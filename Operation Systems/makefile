CC=gcc
OBJS=check.o v1.o v2.o hw1.o function.o
EXEC= v1 v2 check
CFLAGS = -std=c99  #if you have CFLAGS you do not have to write for each file $(CC) -c $*.c!!!

all: $(EXEC)

v1: v1.o hw1.o function.o
	$(CC) -o $@ $^

v2: v2.o hw1.o function.o
	$(CC) -o $@ $^

check: check.o
	$(CC) -o $@ $^



v1.c: hw1.h 
v2.c: hw1.h
hw1.c: hw1.h function.h
function.c: function.h
check.c: check.h


clean: 
	rm -f $(EXEC) $(OBJS)


	

