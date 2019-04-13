#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Child.h"
#include "General.h"
#include "Kindergarten.h"

/**************************************************/
/*             Read a Child from a file           */
/**************************************************/
void readChildTxt(FILE* fp, Child* pChild) {
	//Child ID
	fscanf(fp, "%d", &pChild->id);
	fscanf(fp, "%d", &pChild->age);
}

void readChildBin(FILE* fp, Child* pChild){
	unsigned char info[2];

	unsigned char mask = 31;

	fread(info, sizeof(unsigned char), 2, fp);

	pChild->id = (info[1] & mask) << 8 | info[0];

	pChild->age = info[1] >> 5;
}


/**************************************************/
/*            show a Child				           */
/**************************************************/
void showChild(const Child* pChild) {
	printf("\nID:%d  ", pChild->id);
	printf("Age:%d  ", pChild->age);
}

/**************************************************/
void getChildFromUser(Child* pChild, int id)
/**************************************************/
/**************************************************/
{
	pChild->id = id;

	puts("\nAge:\t");
	scanf("%d", &pChild->age);
}

/**************************************************/
/*Write a Child to the open file				*/
/**************************************************/
void writeChildTxt(FILE* fp, const Child* pChild) {
	fprintf(fp, "%d %d\n", pChild->id, pChild->age);
}

void writeChildBin(FILE* fp, const Child* pChild) {
	unsigned char info[2];
	unsigned char mask = 255;

	info[1] = (pChild->age) << 5 | (pChild->id) >> 8;
	info[0] = pChild->id & mask;

	fwrite(info, sizeof(unsigned char), 2, fp);
}



//linear search
int findChildById(Child** pChildList, int count, int id) {
	Child temp = { id, 0 };
	Child* pTemp = &temp;
	Child** pfound = NULL;

	int index;

	qsort(pChildList, count, sizeof(Child*),
			comperChildrensInTheKinderGartenById);
	pfound = (Child**) bsearch(&pTemp, pChildList, count, sizeof(Child*),
			comperChildrensInTheKinderGartenById);

	if (!pfound) {
		return -1;
	}

	index = pfound-pChildList;
	return index;

}

void birthday(Child* pChild) {
	pChild->age++;
}

//void	releaseChild(Child* pChild)
//{
//	//nothing to release
//}
