/*
 *  Copyright (C) 1991, 1992  Linus Torvalds
 */

/*
 * D. Jeff Dionne, 1995.
 */

/* Additional hole filled: strtol
 * M. Schlifer, NOV 1995.
*/

#include <string.h>
#include <ctype.h>

#ifndef __HAVE_ARCH_BCOPY
void bcopy(const void *src, void *dest, int count)
{
  char *tmp = (char *)dest;
  char *s = (char *)src;
  
  while (count--)
    *tmp++ = *s++;
}
#endif
