/*
 * generic.h
 *
 *  Created on: Jan 4, 2019
 *      Author: yevgeni
 */

#ifndef GENERIC_H_
#define GENERIC_H_




typedef void* DATA;

typedef enum {False, True} BOOL;

typedef struct node {
	DATA key;
	struct node* next;
} NODE;

typedef struct {
	NODE head;
} LIST;



BOOL L_init(LIST* pList);
NODE* L_insert(NODE* pNode, DATA Value);
BOOL L_delete(NODE* pNode);
int L_print(LIST* pList, void (*print)(const void*)) ;
BOOL L_free(LIST* pList);






#endif /* GENERIC_H_ */
