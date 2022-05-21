#include <stdio.h>

int main()
{
	int a = 0b11001001111000111011101001110101;
    int b = 0b00100110100011011011101010000011;
    int c = 0b01100011101010111110010000110010;
    a = a << 22;
    printf("%x\n",a);
    b = b >> 18;
    printf("%x\n",b);
    c = c << 24;
    c = c >> 10;
    printf("%x\n",c);
    int z = a|b|c;

    printf("%x\n",z);
	return 0;
}