#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "City.h"
#include "Kindergarten.h"
#include "General.h"
#include "generic.h"


/**************************************************/
/*read the file from txt file or from binary file */
/**************************************************/
void readCity(City* pCity, int num) {

	if (pCity->pGardenList != NULL) {
		releaseCity(pCity);
		pCity->count = 0;
	}

	if (num == 0) {
		pCity->pGardenList = readAllGardensFromFileTxt(DATA_TXT,
				&(pCity->count));
	} else {
		pCity->pGardenList = readAllGardensFromFileBin(DATA_BINARY,
				&(pCity->count));
	}

	if (pCity->pGardenList == NULL)
		printf("Error reading city information\n");
}

/**************************************************/
/*show the city garden                            */
/**************************************************/
void showCityGardens(City* pCity) {
	showAllGardens(pCity->pGardenList, pCity->count);

}

/**************************************************/
/*show specific garden in the city                */
/**************************************************/
void showSpecificGardenInCity(City* pCity) {
	showGardenMenu(pCity->pGardenList, pCity->count);
}

/**************************************************/
/*save information to binary file or txt file     */
/**************************************************/
void saveCity(City* pCity, int num) {
	if (num == 0) {
		writeGardensToFileTxt(pCity->pGardenList, pCity->count, DATA_TXT);
	} else {
		writeGardensToFileBin(pCity->pGardenList, pCity->count, DATA_BINARY);
	}

}

/**************************************************/
/*add new garden to city                          */
/**************************************************/
void cityAddGarden(City* pCity) {
	pCity->pGardenList = addGarden(pCity->pGardenList, &pCity->count);
	if (pCity->pGardenList == NULL) //Allocation error
		printf("Error adding kindergarten\n");
}

/**************************************************/
/*add new child to specific garden                */
/**************************************************/
void addChildToSpecificGardenInCity(City* pCity) {
	addChildToGarden(pCity->pGardenList, pCity->count);
}

/**************************************************/
/*add age to child in specific garden             */
/**************************************************/
void birthdayToChild(City* pCity) {
	handleBirthdayToChild(pCity->pGardenList, pCity->count);
}

/*********************************************************/
/*count how much chova type of garden we have in the city*/
/*********************************************************/
int countChova(City* pCity) {
	int i;
	int count = 0;
	for (i = 0; i < pCity->count; i++) {
		if (pCity->pGardenList[i]->type == Chova)
			count += pCity->pGardenList[i]->childCount;
	}
	return count;
}

/**********************************/
/*sort by name garden             */
/**********************************/
void sortByNameGarden(City* pCity) {
	insertionSort(pCity->pGardenList, pCity->count, sizeof(pCity->pGardenList),
			comperKinderGartenByName);
}

/**********************************/
/*sort by id                      */
/**********************************/
void sortById(City* pCity) {
	char gardenName[MAX_WORD];
	printf("Print the name of the kindgarten:");
	scanf("%s", gardenName);

	Garden* theGarden = findGardenByName(pCity->pGardenList, pCity->count,
			gardenName);

	insertionSort(theGarden->childPtrArr, theGarden->childCount,
			sizeof(theGarden->childPtrArr),
			comperChildrensInTheKinderGartenById);
}

/**********************************/
/*sort by type and count of child */
/**********************************/
void sortByTypeAndNumOfChildrenInTheKndedrgarten(City* pCity) {
	insertionSort(pCity->pGardenList, pCity->count, sizeof(pCity->pGardenList),
			comperTheTypeAndCountOfKindergarten);

}

/**********************************/
/*       linked list              */
/**********************************/
void LinkedList(City* pcity) {
	int num;

	num = (int) getTypeOption();

	LIST* list = createLinkedListForKindergartenType(pcity, num);

	displayKindergartensFromList(list);

}

/**********************************/
/*free all alocazia               */
/**********************************/
void releaseCity(City* pCity) {
	release(pCity->pGardenList, pCity->count);
}

/**********************************/
/* Create linked list             */
/**********************************/
LIST* createLinkedListForKindergartenType(City* pCity, int type) {
	int i;
	LIST lst;
	LIST* pLst;

	NODE* pNode;

	L_init(&lst);
	pNode = &lst.head;

	for (i = 0; i < pCity->count; i++) {
		if (pCity->pGardenList[i]->type == type) {

			pNode = L_insert(pNode, &pCity->pGardenList[i]);
		}
	}

	pLst = (LIST*) malloc(sizeof(lst));

	if (!pLst) {
		return NULL;
	}
	memcpy(pLst, &lst, sizeof(lst));

	return pLst;
}

/**********************************/
/* print linked list and free     */
/**********************************/
void displayKindergartensFromList(LIST* pLst) {

	L_print(pLst, printGarden);
	L_free(pLst);
}

