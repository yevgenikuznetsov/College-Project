/*
 * func.c
 *
 *  Created on: Dec 7, 2018
 *      Author: yevgeni
 */

#include "func.h"
#include <stdio.h>
#include <ctype.h>
#include <string.h>

//get string from user
void initString(char* str, int size) {
	fgets(str, size, stdin);
}

//print string
void printString(char* str) {
	puts(str);
}

//count how much word in the sentence
int countWords(char* str) {
	int numOfWords = 0;
	char strcopy[strlen(str)];
	char* word;

	strcpy(strcopy, str);
	word = strtok(strcopy, " ");

	while (word != NULL) {
		if (isalpha(*word))  //check if the word is alpha
			numOfWords++;
		word = strtok(NULL, " ");
	}

	return numOfWords;
}

//find the longest word in sentence and convert to upper
void longestInCaptital(char* str) {
	long beginning = 0;
	int countMaxWord = 0;

	while (*str) {
		int count = 0;
		while (!isspace(*str)) {
			count++;
			str++;
		}
		str++;

		//find the longest word , and where the word stated
		if (count > countMaxWord) {
			beginning = (long) (str - count - 1);
			countMaxWord = count;
		}
	}

	changeToUpper(str, beginning, countMaxWord);

}
//switch to uppercase letters
void changeToUpper(char* str, long beginnungWord, int counterMax) {
	int counter = 0;

	str = (char*) (beginnungWord);

	while (counter != counterMax) {
		*str = toupper(*str);
		counter++;
		str++;
	}

}

// revert word in sentence and change special char to *
void revertWords(char* str) {
	int size = strlen(str);

	for (int j = 0; j < strlen(str); j++) {
		char temp = *(str + j);

		if (temp == ',' || temp == ':' || temp == '?' || temp == '-') {
			*(str + j) = ' ';
		}
	}

	while (*str) {
		int count = 0;
		while (!isspace(*str)) {
			count++;
			str++;
		}

		str = str - count;  //where the word starting
		swap(str, count); // swap the word
		str = str + count + 1; // end of the word

	}

	str = str - size;
	//change the special char to '*'
	for (int j = 0; j < strlen(str); j++) {
		if (*(str + j) == ' ') {
			*(str + j) = '*';
		}
	}
}

// swap the word
void swap(char* str, int count) {
	char temp;
	char* tempaddres;

	for (int i = 0; i < count / 2; i++) {
		temp = *(str + i);

		tempaddres = (str + i);
		*tempaddres = *(str + count - i - 1);

		tempaddres = (str + count - i - 1);
		*tempaddres = temp;
	}

}

//erase in the sentence the symbols
void eraseCharsFromString(char* str, const char* symbols) {
	char* words;
	char newString[MAX_LEN] = "\0";

	words = strtok(str, symbols);

	while (words != NULL) {

		strcat(newString, words);
		words = strtok(NULL, symbols);
	}

	strcpy(str, newString);

}

// check if the sentence is palindrome
int isPalindrome(const char *str) {

	const char* left = str + strlen(str); //point to the end
	const char* right = str; // point to start

	while (left > right) {
		if (!isalpha(*left)) {
			left--;
		} else if (!isalpha(*right)) {
			right++;
		} else {
			if (tolower(*right) != tolower(*left)) {
				return 0;
				break;
			} else {
				left--;
				right++;
			}
		}
	}
	return 1;
}

