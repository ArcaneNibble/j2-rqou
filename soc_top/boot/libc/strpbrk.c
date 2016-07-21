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

#ifndef __HAVE_ARCH_STRPBRK
char * strpbrk(const char * cs,const char * ct)
{
  const char *sc1,*sc2;
  
  for( sc1 = cs; *sc1 != '\0'; ++sc1) {
    for( sc2 = ct; *sc2 != '\0'; ++sc2) {
      if (*sc1 == *sc2)
	return (char *) sc1;
    }
  }
  return NULL;
}
#endif
