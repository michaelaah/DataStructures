/*
 * Description: This ArrayList.cpp file implements the function declarations of the ArrayList.h file.
 * The ArrayList class and its corresponding functions implement the behavior of a resizeable Array List. Resizing
 * the underlying array depends on the load factor, the current number of elements divided by the size of the array.
 * If the load factor equals or surpasses 1/2, another array is allocated with twice the size of the original array.
 * If the load factor equals or falls below 1/4, another array is allocated with half the size of the original array.
 * In both cases, after having allocated a new array, all elements in the original array are copied over into the 
 * new array and the reference to the class's array is updated to be this new array.
 */
#pragma once
#include "ArrayList.h"

/**
 * The ArrayList constructor takes no arguments, assigns the size of the ArrayList to be 1, assigns 0 to be the
 * number of elements in the array, and allocates an int type array of size 1.
 */
ArrayList::ArrayList(){
    listSize      = 1;
    elementCount  = 0;
    list          = new int[listSize];
}

/**
 * The reAlloc function allocates more memory for the underlying array such that the newly allocated array is
 * twice the size of the original array. All elements in the original array are copied over into the new array
 * and the ArrayList's reference to the array is updated to be the new array.
 */
void ArrayList::reAlloc() {
    listSize  *= 2;
    int* arr2 = new int[listSize];

    // Copying all elements into the newly allocated array.
    for(int i = 0; i < elementCount; i++){
        arr2[i] = list[i];
    }

    // Update ArrayList reference to be the new array.
    list = arr2;
}

/**
 * The deAlloc function creates a new array by allocating memory of half the size of the original array. All
 * elements in the original array are copied over into the new array and the ArrayList's reference to the
 * array is updated to be the new array.
 */
void ArrayList::deAlloc(){
    listSize  /= 2;
    int* arr2 = new int[listSize];

    // Copying all elements into the newly allocated array.
    for(int i = 0; i < elementCount; i++){
        arr2[i] = list[i];
    }

    // Update ArrayList reference to be the new array.
    list = arr2;
}

/**
 * The addAt function adds an element x at position index to the ArrayList.
 * @param index The position in the array to add int x.
 * @param x The element to be added to the ArrayList at position index.
 * @return True if int x was successfully added to the underlying array, otherwise false is returned.
 */
bool ArrayList::addAt(int index, int x){

    // If the index is out of bounds, return false.
    if(index > listSize || index < 0){
        return false;
    }

    // If adding this element would add more elements that the current size of the array, call the reAlloc function.
    if((elementCount + 1) >= listSize){
        reAlloc();
    }

    // Move all elements over to make space at position index in the underlying array.
    for (int i = elementCount; i >= index; i--) {
        list[i + 1] = list[i];
    }

    // Insert int x at position index in the array.
    list[index] = x;

    // Increment the number of elements currently in the array.
    elementCount++;

    return true;
}

/**
 * The add function appends an element x to the end of the ArrayList.
 * @param x The element to be added to the ArrayList.
 * @return True if x was successfully added to the underlying array, otherwise false is returned.
 */
bool ArrayList::add(int x) {

    // return the result of addAt when called on the current number of elements as the index of the underlying array.
    return addAt(elementCount, x);
}

/**
 * The addFirst function inserts an element x into the ArrayList.
 * @param x The element to be added to the ArrayList.
 * @return True if x was successfully added to the underlying array, otherwise false is returned.
 */
bool ArrayList::addFirst(int x) {

    // return the result of addAt when called on index 0 of the underlying array.
    return addAt(0,x);
}

/**
 * The get function returns the element stored at position index in the ArrayList.
 * @param index The position in the ArrayList of the element to retrieve.
 * @return an integer representing the element at position index in the underlying array in the ArrayList class.
 * If the index is out of bounds, a -1 is returned.
 */
int ArrayList::get(int index){

    // If the index is out of bounds, return -1.
    if(index < 0 || index >= elementCount){
        return -1;

    } else{
        return list[index];
    }
}

/**
 * The removeAt function removes the element at position index from the ArrayList.
 * @param index The position of the element to be removed from the ArrayList.
 * @return The element removed is returned if the index is not out of bounds, otherwise -1 is returned.
 */
int ArrayList::removeAt(int index){

    // If the index is out of bounds, return -1.
    if(index < 0 || index >= elementCount){
        return -1;

    } else{

        // Retrieve the element at position index.
        int x = list[index];

        // Move all elements down from the end of the array.
        for(int i = index; i < elementCount - 1; i++){
            list[i] = list[i + 1];
        }

        // Decrement the number of elements currently in the array.
        elementCount--;

        // If the removal of this element brings the load factor equal to or below 1/4, then call the deAlloc function.
        if(elementCount <= listSize / 4){
            deAlloc();
        }

        return x;
    }
}

/**
 * The removeLast function removes the last element from the ArrayList.
 * @return The element removed is returned if the list os not empty, otherwise the list is empty and a -1 is returned.
 */
int ArrayList::removeLast(){

    // Return result of calling removeAt with the number of elements currently in the ArrayList as the index.
    return removeAt(this->getElements());
}

/**
 * The removeFirst function removes the first element from the ArrayList.
 * @return The element removed is returned if the list os not empty, otherwise the list is empty and a -1 is returned.
 */
int ArrayList::removeFirst(){

    // Return result of calling removeAt with 0 as the index.
    return removeAt(0);
}

/**
 * The swap function changes the positions of the two elements in positions a and b.
 * @param a The index of the first element to have its position swapped.
 * @param b The index of the second element to have its position swapped.
 * @return If both indices are in bounds, then true is returned. Otherwise, false 
 * is returned as at least one of the indices is out of bounds.
 */
bool ArrayList::swap(int a, int b){

    // If either index a or b is out of bounds, return false.
    if(a < 0 || b < 0 || a >= elementCount || b >= elementCount){
        return false;

    // Otherwise, swap the elements in indices a and b and return true.
    } else{
        int temp = list[a];
        list[a]   = list[b];
        list[b]   = temp;
        return true;
    }
}

/**
 * The getElements function returns the count of the number of elements currently stored in the ArrayList.
 * @return th count of the number of the elements currently stored in the ArrayList.
 */
int ArrayList::getElements() {
    return elementCount;
}

/**
 * the getSize function returns the size of the underlying array stored in the ArrayList class.
 * @return the size of the array stored in the ArrayList class.
 */
int ArrayList::getSize() {
    return listSize;
}