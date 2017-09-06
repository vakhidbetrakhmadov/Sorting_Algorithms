package test;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Integer[] arr = RandomArrayGenerator.getIntegerArray(20, 100);
        RandomArrayGenerator.printArray(arr);
        QuickSort.sort(arr);
        RandomArrayGenerator.printArray(arr);
    }
}

class RandomArrayGenerator {

    public static Integer[] getIntegerArray(int size, int range) {
        Integer[] arrayOfIntegers = new Integer[size];
        Random randomNumberGenerator = new Random();
        for (int i = 0; i < size; ++i)
            arrayOfIntegers[i] = randomNumberGenerator.nextInt(range);
        return arrayOfIntegers;
    }

    public static <T> void printArray(T[] array) {
        for (T anArray : array) {
            System.out.print(anArray + " ");
        }
        System.out.println();
    }
}

class QuickSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int first, int last) {
        if (first < last) {
            int pivotIndex = partition(array, first, last);
            quickSort(array, 0, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, last);
        }

    }

    private static <T extends Comparable<T>> int partition(T[] array, int first, int last) {
        sort3(array, first, last);
        T pivotValue = array[first];
        int up = first;
        int down = last;
        do {
            while (up < last && array[up].compareTo(pivotValue) <= 0)
                ++up;
            while (array[down].compareTo(pivotValue) > 0)
                --down;
            if (up < down)
                swap(array, up, down);
        } while (up < down);
        swap(array, first, down);
        return down;
    }

    private static <T extends Comparable<T>> void sort3(T[] array, int first, int last) {
        int middle = (first + last) / 2;
        if (array[middle].compareTo(array[first]) < 0)
            swap(array, first, middle);
        if (array[last].compareTo(array[middle]) < 0)
            swap(array, last, middle);
        if (array[middle].compareTo(array[first]) < 0)
            swap(array, first, middle);
    }

    private static <T extends Comparable<T>> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}

class HeapSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        buildHeap(array); //reverse heap
        shrinkHeap(array);
    }

    private static <T extends Comparable<T>> void buildHeap(T[] array) {
        int n = 1; //heap size
        while (n < array.length) {
            n++; // add new element
            int child = n - 1;
            int parent = (child - 1) / 2;
            while (parent >= 0 && array[parent].compareTo(array[child]) < 0) {
                swap(array, parent, child);
                child = parent;
                parent = (child - 1) / 2;
            }
        }
    }

    private static <T extends Comparable<T>> void shrinkHeap(T[] array) {
        int n = array.length; // heap size
        while (n > 1) {
            n--; // poll the heap
            swap(array, 0, n); // move the polled value to the end
            restoreHeapProperty(array, n);
        }
    }

    private static <T extends Comparable<T>> void restoreHeapProperty(T[] array, int heapSize) {
        int parent = 0;
        while (true) {
            int leftChild = 2 * parent + 1;
            int rightChild = 2 * parent + 2;
            if (leftChild >= heapSize)
                return;
            int maxChild = leftChild;
            if (rightChild < heapSize && array[leftChild].compareTo(array[rightChild]) < 0)
                maxChild = rightChild;
            if (array[parent].compareTo(array[maxChild]) < 0) {
                swap(array, parent, maxChild);
                parent = maxChild;
            } else
                return;
        }
    }

    private static <T extends Comparable<T>> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}

class MergeSort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array.length > 1) {
            int leftArraySize = array.length / 2;
            T[] leftArray = (T[]) new Comparable[leftArraySize];
            T[] rightArray = (T[]) new Comparable[array.length - leftArraySize];
            System.arraycopy(array, 0, leftArray, 0, leftArraySize);
            System.arraycopy(array, leftArraySize, rightArray, 0, array.length - leftArraySize);
            sort(leftArray);
            sort(rightArray);
            merge(array, leftArray, rightArray);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] destArray, T[] leftArray, T[] rightArray) {
        int i = 0, j = 0, k = 0;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i].compareTo(rightArray[j]) < 0)
                destArray[k++] = leftArray[i++];
            else
                destArray[k++] = rightArray[j++];
        }
        if (i < leftArray.length)
            System.arraycopy(leftArray, i, destArray, k, leftArray.length - i);
        else
            System.arraycopy(rightArray, j, destArray, k, rightArray.length - j);
    }
}

class ShellSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        int gap = array.length / 2;
        while (gap > 0) {
            for (int i = gap; i < array.length; ++i)
                insert(array, i, gap);

            if (gap == 2)
                gap = 1;
            else
                gap /= 2.2;
        }
    }

    private static <T extends Comparable<T>> void insert(T[] array, int toInsertIndex, int gap) {
        T toInsertValue = array[toInsertIndex];
        while (toInsertIndex > gap - 1 && array[toInsertIndex - gap].compareTo(toInsertValue) > 0) {
            array[toInsertIndex] = array[toInsertIndex - gap];
            toInsertIndex -= gap;
        }
        array[toInsertIndex] = toInsertValue;
    }
}

class InsertionSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 1; i < array.length; ++i)
            insert(array, i);
    }

    private static <T extends Comparable<T>> void insert(T[] array, int toInsertIndex) {
        T toInsertValue = array[toInsertIndex];
        while (toInsertIndex > 0 && array[toInsertIndex - 1].compareTo(toInsertValue) > 0) {
            array[toInsertIndex] = array[toInsertIndex - 1];
            --toInsertIndex;
        }
        array[toInsertIndex] = toInsertValue;
    }
}

class SelectionSort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        int min;
        for (int i = 0; i < array.length - 1; ++i) {
            min = i;
            for (int j = i + 1; j < array.length; ++j) {
                if (array[j].compareTo(array[min]) < 0)
                    min = j;
            }
            if (min != i)
                swap(array, i, min);
        }
    }

    private static <T extends Comparable<T>> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}


class BubbleSort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        boolean swapDone = true;
        int iteration = 1;
        while (swapDone) {
            swapDone = false;
            for (int i = 0; i < array.length - iteration; ++i) {
                if (array[i + 1].compareTo(array[i]) < 0) {
                    swapDone = true;
                    swap(array, i, i + 1);
                }
            }
            ++iteration;
        }
    }

    private static <T extends Comparable<T>> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}

