#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Kindergarten.h"
#include "Child.h"
#include "General.h"
#include <stdarg.h>

const char* GardenTypeName[NofTypes] = { "Chova", "Trom Chova",
		"Trom Trom Chova" };

//**************************************************
// Read data off all Kindergartens from txt file
//**************************************************
Garden** readAllGardensFromFileTxt(char* fileName, int* pGardenCount) {
	int count, i;
	FILE *fp;
	Garden** gardens;

	*pGardenCount = 0;

	if ((fp = fopen(fileName, "rt")) == NULL) {
		printf("Cannot Open File '%s'", fileName);
		return NULL;
	}

	fscanf(fp, "%d", &count);

	//Would like to do the allocation even if count ==0
	//so will not return NULL as error
	gardens = (Garden**) malloc(count * sizeof(Garden*));

	if (!checkAllocation(gardens))
		return NULL;

	for (i = 0; i < count; i++) {
		gardens[i] = (Garden*) malloc(sizeof(Garden));
		if (!checkAllocation(gardens[i]))
			return NULL;
		readGardenTxt(fp, gardens[i]);
	}

	fclose(fp);

	*pGardenCount = count;
	return gardens;

}

//**************************************************
// Read data off all Kindergartens from binary file
//**************************************************
Garden** readAllGardensFromFileBin(char* fileName, int* pGardenCount) {
	int count, i;
	FILE *fp;
	Garden** gardens;

	*pGardenCount = 0;

	if ((fp = fopen(fileName, "rb")) == NULL) {
		printf("Cannot Open File '%s'", fileName);
		return NULL;
	}

	fread(&count, sizeof(int), 1, fp);

	gardens = (Garden**) malloc(count * sizeof(Garden*));

	if (!gardens)
		return NULL;

	for (i = 0; i < count; i++) {
		gardens[i] = (Garden*) malloc(sizeof(Garden));
		if (!checkAllocation(gardens[i])) {
			fclose(fp);
			return NULL;
		}

		readGardenBin(fp, gardens[i]);
	}

	fclose(fp);

	*pGardenCount = count;
	return gardens;
}

/**************************************************/
/*         Read a Kindergarten from a txt file    */
/**************************************************/
void readGardenTxt(FILE* fp, Garden* pGarden) {
	int i, type;
	char sTemp[MAX_CHAR];

	//Kindergarten Name
	//Get it to a temp string and then create the pointer to save
	//in the struct in exact length.
	fscanf(fp, "%s", sTemp);
	pGarden->name = getStrExactLength(sTemp);

	//Kindergarten type
	fscanf(fp, "%d", &type);
	pGarden->type = (GardenType) type;

	//Child count
	fscanf(fp, "%d", &pGarden->childCount);
	if (pGarden->childCount == 0) {
		pGarden->childPtrArr = NULL;
		return;
	}

	pGarden->childPtrArr = (Child**) malloc(
			pGarden->childCount * sizeof(Child*));
	if (!checkAllocation(pGarden->childPtrArr))
		return;

	//Read each child
	for (i = 0; i < pGarden->childCount; i++) {
		pGarden->childPtrArr[i] = (Child*) malloc(sizeof(Child));
		if (!checkAllocation(pGarden->childPtrArr[i]))
			return;
		readChildTxt(fp, pGarden->childPtrArr[i]);
	}

}

/**************************************************/
/*      Read a Kindergarten from a binary file    */
/**************************************************/

void readGardenBin(FILE* fp, Garden* pGarden) {
	unsigned char info[1];
	unsigned char mask = 3;
	int len2, i;

	fread(&len2, sizeof(int), 1, fp);
	pGarden->name = (char*) malloc(len2 * sizeof(char));
	fread(pGarden->name, sizeof(char), len2, fp);

	fread(info, sizeof(unsigned char), 1, fp);

	pGarden->childCount = info[0] >> 2;
	if (pGarden->childCount == 0) {
		pGarden->childPtrArr = NULL;
		return;
	}
	pGarden->type = info[0] & mask;

	pGarden->childPtrArr = (Child**) malloc(
			pGarden->childCount * sizeof(Child*));
	if (!checkAllocation(pGarden->childPtrArr))
		return;

	for (i = 0; i < pGarden->childCount; i++) {
		pGarden->childPtrArr[i] = (Child*) malloc(sizeof(Child));
		if (!checkAllocation(pGarden->childPtrArr[i]))
			return;
		readChildBin(fp, pGarden->childPtrArr[i]);
	}

}

/**************************************************/
/* show all Kindergartens and variadict function  */
/**************************************************/
void showAllGardens(Garden** pGardenList, int count) {
	int i;
	printf("There are %d kindergarten in the city\n", count);
	for (i = 0; i < count; i++) {
		printf("\nKindergarten %d:\n", i + 1);
		showGarden(pGardenList[i]);
		printf("\n");
	}
	printf("\n");

	if (count > MAX_NUM) {
		printFromVariadicFunction(pGardenList[0]->name,
				pGardenList[0]->childCount, pGardenList[1]->name,
				pGardenList[1]->childCount, pGardenList[2]->name,
				pGardenList[2]->childCount, NULL);
	}
}

/**************************************************/
/*            show a Kindergarten		           */
/**************************************************/
void showGarden(const Garden* pGarden) {
	int i;

	// Kindergarten name
	printf("Name:%s", pGarden->name);
	printf("\tType:%s", GardenTypeName[pGarden->type]);
	printf("\t Children:%d", pGarden->childCount);
	//all Children
	for (i = 0; i < pGarden->childCount; i++)
		showChild(pGarden->childPtrArr[i]);
}

/**************************************************/
/*     write Kindergartens to a txt file          */
/**************************************************/

void writeGardensToFileTxt(Garden** pGardenList, int gardenCount,
		char* fileName) {
	int i;
	FILE *fp;
	if ((fp = fopen(fileName, "wt")) == NULL) {
		printf("Cannot Open File '%s'", fileName);
		return;
	}
	fprintf(fp, "%d\n", gardenCount);
	for (i = 0; i < gardenCount; i++)
		writeGardenTxt(fp, pGardenList[i]);

	fclose(fp);
}

/**************************************************/
/*     write Kindergartens to a binary file	      */
/**************************************************/
void writeGardensToFileBin(Garden** pGardenList, int gardenCount,
		char* fileName) {
	int i;
	FILE *fp;
	if ((fp = fopen(fileName, "wt")) == NULL) {
		printf("Cannot Open File '%s'", fileName);
		return;
	}

	fwrite(&gardenCount, sizeof(int), 1, fp);

	for (i = 0; i < gardenCount; i++)
		writeGardenBin(fp, pGardenList[i]);

	fclose(fp);

}

/**************************************************/
/*  Write a Kindergarten to the open txt file     */
/**************************************************/
void writeGardenTxt(FILE* fp, const Garden* pGarden) {
	int i;
	//Kindergarten Name
	fprintf(fp, "%s", pGarden->name);
	fprintf(fp, "  %d", pGarden->type);

	//Children
	fprintf(fp, " %d\n", pGarden->childCount);

	for (i = 0; i < pGarden->childCount; i++)
		writeChildTxt(fp, pGarden->childPtrArr[i]);
}

/**************************************************/
/*  Write a Kindergarten to the open binary file  */
/**************************************************/

void writeGardenBin(FILE* fp, const Garden* pGarden) {
	int i, len;
	unsigned char info[1];
	//Kindergarten Name

	len = strlen(pGarden->name) + 1;
	fwrite(&len, sizeof(int), 1, fp);
	fwrite(pGarden->name, sizeof(char), len, fp);

	//Children

	info[0] = (pGarden->childCount) << 2 | pGarden->type;

	fwrite(info, sizeof(unsigned char), 1, fp);

	for (i = 0; i < pGarden->childCount; i++)
		writeChildBin(fp, pGarden->childPtrArr[i]);

}

//*************************************************
// menu option to add Child to a Kindergarten
//*************************************************
void addChildToGarden(Garden** pGardenList, int gardenCount) {
	Garden* pGarden;
	if (pGardenList == NULL) {
		printf("There are no Kindergartens yet\n");
		return;
	}

	pGarden = getGardenAskForName(pGardenList, gardenCount);
	if (pGarden == NULL) {
		printf("no such Kindergarten\n");
		return;
	}

	pGarden->childPtrArr = (Child**) realloc(pGarden->childPtrArr,
			(pGarden->childCount + 1) * sizeof(Child*));
	if (!checkAllocation(pGarden->childPtrArr)) {
		pGarden->childCount = 0;
		return;
	}

	pGarden->childPtrArr[pGarden->childCount] = (Child*) malloc(sizeof(Child));
	getChildCheckIdFromUser(pGarden->childPtrArr[pGarden->childCount], pGarden);
	pGarden->childCount++;
}

/**************************************************/
/* Add a Kindergarten  - we need to allocate more space   */
/* in the list.									*/
/**************************************************/
Garden** addGarden(Garden** pGardenList, int* pGardenCount) {
	pGardenList = (Garden**) realloc(pGardenList,
			(*pGardenCount + 1) * sizeof(Garden*));
	if (!checkAllocation(pGardenList)) {
		*pGardenCount = 0;
		return NULL;
	}

	Garden* temp = (Garden*) malloc(sizeof(Garden));
	getGardenFromUser(temp, pGardenList, *pGardenCount);
	pGardenList[*pGardenCount] = temp;
	(*pGardenCount)++;

	return pGardenList;

}

/**************************************************/
/*  shoe the menu to user                         */
/**************************************************/

void showGardenMenu(Garden** pGardenList, int count) {
	Garden* pGarden;

	pGarden = getGardenAskForName(pGardenList, count);

	if (pGarden != NULL)
		showGarden(pGarden);
	else
		printf("No such Kindergarten\n");

}

/**************************************************/
/*  get name of garden from  user                 */
/**************************************************/

Garden* getGardenAskForName(Garden** pGardenList, int count) {
	char sTemp[100];
	Garden* pGarden;

	//Kindergarten name
	puts("\nGive me the Kindergarten Name:\t");
	scanf("%s", sTemp);
	getchar(); //remove \n

	//In this exe the find will not be efficient
	pGarden = findGardenByName(pGardenList, count, sTemp);

	return pGarden;
}

/**************************************************/
/*  find garden by name                           */
/**************************************************/

Garden* findGardenByName(Garden** pGardenList, int count, const char* name) {
	//linear search
	int i;

	for (i = 0; i < count; i++) {
		if (strcmp(pGardenList[i]->name, name) == 0)
			return pGardenList[i];
	}

	return NULL;
}

/**************************************************/
/*  get id from user                              */
/**************************************************/

Child* getChildAskForId(Garden* pGarden) {
	int id;
	int index;

	printf("Enter child id\n");
	scanf("%d", &id);

	//The search will be inefficient - so sort
	index = findChildById(pGarden->childPtrArr, pGarden->childCount, id);
	if (index == -1)
		return NULL;

	return pGarden->childPtrArr[index];
}

void getGardenFromUser(Garden* pGarden, Garden** pGardenList, int count) {
	int i;
	char sTemp[100];
	int bOK = 0;
	int countC;

	do {
		//Kindergarten name
		puts("\nName:\t");
		scanf("%s", sTemp);
		getchar(); //remove second word if exsist
		if (findGardenByName(pGardenList, count, sTemp) != NULL)
			printf("This Kindergarten already in list\n");
		else
			bOK = 1;
	} while (!bOK);

	pGarden->name = getStrExactLength(sTemp);

	pGarden->type = getTypeOption();

	//Children
	puts("\nEnter children Details:\t");

	puts("\nChildren count:");
	scanf("%d", &countC);
	if (countC == 0) {
		pGarden->childCount = 0;
		pGarden->childPtrArr = NULL;
		return;
	}

	//create the list of children in the correct size
	pGarden->childPtrArr = (Child**) malloc(countC * sizeof(Child*));
	if (!checkAllocation(pGarden->childPtrArr)) {
		pGarden->childPtrArr = NULL;
		return;
	}

	pGarden->childCount = 0;
	for (i = 0; i < countC; i++) {
		pGarden->childPtrArr[i] = (Child*) malloc(sizeof(Child));
		getChildCheckIdFromUser(pGarden->childPtrArr[i], pGarden);
		pGarden->childCount++;
	}

}

/**************************************************/
/* Init a child from use. Ask for Id, check it is */
/* new and after call the function to ask for all other */
/* information										*/
/**************************************************/
void getChildCheckIdFromUser(Child* pChild, const Garden* pGarden) {
	int id, bOK = 0;
	do {
		puts("\nID No.:\t");
		scanf("%d", &id);
		if (findChildById(pGarden->childPtrArr, pGarden->childCount, id) != -1)
			printf("This child is in the Kindergarten\n");
		else
			bOK = 1;
	} while (!bOK);

	getChildFromUser(pChild, id);
}

/**************************************************/
/*  add child age                                 */
/**************************************************/

void handleBirthdayToChild(Garden** pGardenList, int count) {
	Garden* pGarden;
	Child* pChild;

	pGarden = getGardenAskForName(pGardenList, count);
	if (pGarden == NULL) {
		printf("No such Kindergarten\n");
		return;
	}

	pChild = getChildAskForId(pGarden);
	if (pChild == NULL) {
		printf("No such child\n");
		return;
	}
	birthday(pChild);
}

GardenType getTypeOption() {
	int i, type;
	printf("Garden type:\n");
	do {
		for (i = 0; i < NofTypes; i++)
			printf("Enter %d for %s\n", i, GardenTypeName[i]);
		scanf("%d", &type);
	} while (type < 0 || type >= NofTypes);
	return (GardenType) type;
}

/**************************************************/
/*  comper kindergarten by name                   */
/**************************************************/
int comperKinderGartenByName(const void* gardenName1, const void* gardenName2) {

	const Garden* garden1 = *(const Garden**) gardenName1;
	const Garden* garden2 = *(const Garden**) gardenName2;

	return strcmp(garden1->name, garden2->name);

}

/**************************************************/
/*  comper children by id in the kindergarten     */
/**************************************************/
int comperChildrensInTheKinderGartenById(const void* child1, const void* child2) {

	const Child* kid1 = *(const Child**) child1;
	const Child* kid2 = *(const Child**) child2;

	return ((kid1->id) - (kid2->id));

}

/**************************************************/
/* the first comper the type of the garter        */
/*the second comper if type equals by count child */
/**************************************************/
int comperTheTypeAndCountOfKindergarten(const void* gartenType1,
		const void* gartenType2) {
	int result;
	const Garden* garden1 = *(const Garden**) gartenType1;
	const Garden* garden2 = *(const Garden**) gartenType2;

	result = ((garden2->type) - (garden1->type));

	if (result == 0) {
		result = ((garden1->childCount) - (garden2->childCount));
	}

	return result;

}

/**************************************************/
/*print from verdict function by name and count   */
/**************************************************/
void printFromVariadicFunction(char* gardenName, ...) {

	va_list list;

	char* currentName;
	int currentGrade;

	va_start(list, gardenName);

	currentName = gardenName;

	while (currentName != NULL) {
		currentGrade = va_arg(list, int);
		printf("%-10s ----> %d\n", currentName, currentGrade);
		currentName = va_arg(list, char*);

	}
	va_end(list);

}

void printGarden(const void* pPoint) {
	Garden* gard = *(Garden**) pPoint;
	showGarden(gard);
}

// release the Children list
//release the name ptr of each Kindergarten
//release the Kindergarten list
void release(Garden** pGardenList, int count) {
	int i;
	for (i = 0; i < count; i++) {
		free(pGardenList[i]->childPtrArr);
		free(pGardenList[i]->name);
		free(pGardenList[i]);
	}

	free(pGardenList);

}

