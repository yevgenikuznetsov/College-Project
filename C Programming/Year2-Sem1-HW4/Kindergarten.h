#ifndef __KINDERGARTEN__
#define __KINDERGARTEN__

#include "Child.h"

#define MAX_NUM 3
#define MAX_CHAR 100

typedef enum {
	Chova, TromChova, TromTromChova, NofTypes
} GardenType;

const char* GardenTypeName[NofTypes];

typedef struct {
	char* name;
	GardenType type;
	Child** childPtrArr;
	int childCount;
} Garden;

Garden** readAllGardensFromFileTxt(char* fileName, int* pGardenCount);
Garden** readAllGardensFromFileBin(char* fileName, int* pGardenCount);

void readGardenTxt(FILE* fp, Garden* pGarden);
void readGardenBin(FILE* fp, Garden* pGarden);

GardenType getTypeOption();

void showAllGardens(Garden** pGardenList, int count);
void showGarden(const Garden* pGarden);

void writeGardensToFileTxt(Garden** pGardenList, int gardenCount,
		char* fileName);
void writeGardensToFileBin(Garden** pGardenList, int gardenCount,
		char* fileName);

void writeGardenTxt(FILE* fp, const Garden* pGarden);
void writeGardenBin(FILE* fp, const Garden* pGarden);

void addChildToGarden(Garden** pGardenList, int gardenCount);
Garden** addGarden(Garden** pGardenList, int* pGardenCount);

void showGardenMenu(Garden** pGardenList, int count);
Garden* getGardenAskForName(Garden** pGardenList, int count);

Garden* findGardenByName(Garden** pGardenList, int count, const char* name);

Child* getChildAskForId(Garden* pGarden);

void getGardenFromUser(Garden* pGarden, Garden** pGardenList, int count);
void getChildCheckIdFromUser(Child* pChild, const Garden* pGarden);

void handleBirthdayToChild(Garden** pGardenList, int count);

int comperKinderGartenByName(const void* gardenName1, const void* gardenName2);
int comperChildrensInTheKinderGartenById(const void* child1, const void* child2);
int comperTheTypeAndCountOfKindergarten(const void* gartenType1,
		const void* gartenType2);

void printFromVariadicFunction(char* gardenName, ...);
void printGarden(const void* pPoint);

void release(Garden** pGardenList, int count);

#endif
