#include "sys/types.h"
#include "sys/wait.h"
#include "unistd.h"
#include "stdlib.h"
#include "stdio.h"
#include "signal.h"

int main()
{
    int pid;
    printf("%d\n",pid); fflush(stdout);
    if (fork() == 0){
        printf("A"); fflush(stdout);
        exit(1);
    }

    if ((pid = fork()) == 0){
        printf("B"); fflush(stdout);
        exit(2);
    }

    printf("C"); fflush(stdout);
    
    printf("D"); fflush(stdout);
    printf("%d",pid); fflush(stdout);
    waitpid(pid, NULL, 0);

    printf("E"); fflush(stdout);
    exit(0);
}
