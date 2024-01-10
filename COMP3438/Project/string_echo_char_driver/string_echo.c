#include <linux/module.h>
#include <linux/cdev.h>
#include <linux/kdev_t.h>
#include <linux/init.h>
#include <linux/fs.h>
#include <linux/types.h>
#include <asm/uaccess.h>
#include <mach/gpio.h> 
#include <plat/gpio-cfg.h> 

#define DRIVER_NAME "String Echo Driver" // Optional 1
#define LED1 S5PV210_GPJ2(0)
#define LED_ON 0
#define LED_OFF 1

#define N_D 1
#define S_N 1
static dev_t devno;
static int major;
static struct cdev form;

static int led1_open(struct inode *inode, struct file *fp) {
    printk("Device " DRIVER_NAME " open.\n");
    return 0;
}

static ssize_t led1_write(struct file *fp, const char __user *buf, size_t count, loff_t *offset) {
    char led_status[count]; // Changed 1
    int ret;

    ret = copy_from_user(led_status, buf, sizeof(led_status)); // Changed 2

    if (ret) {
        printk("Fail to copy data from the user space to the kernel space.\n");
        return 0;
    }

    printk("Echo: %s\n", led_status); // Changed 3

    // Changed
    // if (led_status > '0')
    //     gpio_set_value(LED1, LED_ON);
    // else 
    //     gpio_set_value(LED1, LED_OFF);
    
    return sizeof(led_status);
} 

static int led1_release(struct inode *inode, struct file *fp) {
    printk("Device " DRIVER_NAME " release.\n");
    return 0;
}

static struct file_operations fops = {
    owner: THIS_MODULE, 
    open: led1_open,
    write: led1_write,
    release: led1_release,
};

static int __init led1_init(void) {
    int ret;
    // s3c_gpio_cfgpin(LED1, S3C_GPIO_OUTPUT); // Changed 5
    // gpio_set_value(LED1, LED_OFF); // Changed
    
    ret = alloc_chrdev_region(&devno, S_N, N_D, DRIVER_NAME);
    if (ret < 0) {
        printk("Device " DRIVER_NAME " cannot get major number.\n");
        return ret;
    }
    major = MAJOR(devno);
    printk("Device " DRIVER_NAME " initialized (Major number = %d).\n", major);

    cdev_init(&form, &fops);
    form.owner = THIS_MODULE;
    ret = cdev_add(&form, devno, N_D);
    if (ret) {
        printk("Device " DRIVER_NAME " register fail.\n");
        return ret;
    }
    return 0;
}

static void __exit led1_exit(void) {
    cdev_del(&form);
    unregister_chrdev_region(devno, N_D);
    printk("Device " DRIVER_NAME " unloaded.\n");
}


module_init(led1_init);
module_exit(led1_exit);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("XXX"); // Optional 2
MODULE_DESCRIPTION("String Echo Driver"); // Optional 3
