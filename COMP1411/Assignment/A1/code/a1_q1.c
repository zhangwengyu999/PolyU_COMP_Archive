#include <stdio.h>

int main()
{
	int x = 2;
    int y = (x<<7)-(x<<5)-(x<<4)-(x<<1)-x;
    printf("%d",y);
	return 0;
}