CC = sh2elf-gcc
LD = sh2elf-ld
AR = sh2elf-ar
OBJCOPY = sh2elf-objcopy
RANLIB = sh2elf-ranlib
LIBGCC = `sh2elf-gcc -print-file-name=libgcc.a`
CFLAGS = -m2 -g -Os -Wall -Iinclude
LDFLAGS = -T sh32.x -Map main.map

all: interrupts.img rte.img

interrupts.elf: interrupts.o
rte.elf: rte.o

%.o: %.S
	$(CC) $(CFLAGS) -c $< -o $@

%.elf:
	$(LD) $(LDFLAGS) $^ $(LIBGCC) -o $@

%.img: %.elf
	$(OBJCOPY) -v -S -O binary --srec-forceS3 $< $@

clean:
	rm -f *.o *.elf *.img *.map

.PHONY: clean
