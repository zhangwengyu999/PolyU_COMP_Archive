#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h> // Added 1

int main() {
    int fd;    
    char input[256]; // Changed 2
    
    fd = open("/dev/stringecho", O_WRONLY); // Changed 3

    if (fd == -1) {
        perror("Fail to open /dev/stringecho.\n");
        return -1;
    }
    
    while (1) {
        printf("User: input string, q to quit\n"); // Changed 4
        fgets(input, sizeof(input), stdin); // Change 5

        int len = strlen(input); // Change 6
        if (len > 0) input[len - 1] = '\0';

        if (strncmp(input, "q", 1) == 0) break; // Changed 7 option
        write(fd, input, len); // Changed 8
    }
    close(fd);
    return 0;
}
