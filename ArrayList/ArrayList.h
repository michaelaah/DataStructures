/*
 * Description: The ArrayList class and its corresponding functions implement the behavior of
  a resizeable array list. The implementation of these functions can be found in the ArrayList.cpp file.
 */

#ifndef __ARRAYLIST_HEADER
#define __ARRAYLIST_HEADER

class ArrayList{
    public:
        ArrayList();
        bool    add(int);
        bool    addAt(int, int);
        bool    addFirst(int);
        int     get(int);
        int     removeAt(int);
        int     removeFirst();
        int     removeLast();
        bool    swap(int,int);

        int     getElements();
        int     getSize();

    private:
        int     listSize;
        int     elementCount;
        int*    list;
        void    reAlloc();
        void    deAlloc();
};

#endif