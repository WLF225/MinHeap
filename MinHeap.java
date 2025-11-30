public class MinHeap<T extends Comparable<T>> {

    T[] heap;
    int size = 0;

    public MinHeap(int size){
        heap = (T[]) new Comparable[size];
    }

    public void insert(T data){
        //To make sure the heap still have space
        if (size < heap.length){
            heap[size] = data;
            //Swim the new element to the right position
            swim(size++);
        }else{
            //To resize the array
            T[] temp = (T[]) new Comparable[size*2];
            for (int i = 0; i < size; i++)
                temp[i] = heap[i];
            heap = temp;
            insert(data);
        }
    }

    public T deleteMin(){

        T temp = heap[0];
        //To swap the min with last element and reduce the size so the heap won't read the min element
        swap(heap,0,--size);
        //To sink the value we just swapped to the right position
        //The min value will become in position 0 since it was in position 1 or 2
        sink(0);
        return temp;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public T getMin(){
        return heap[0];
    }

    public void swim(int index){
        //To check it until it have no parent
        while (index != 0){
            //To check if it is smaller than it is parent
            if (heap[(index-1)/2].compareTo(heap[index]) > 0){
                //To swap it with it's parent
                T temp = heap[(index-1)/2];
                heap[(index-1)/2] = heap[index];
                heap[index] = temp;
                index = (index-1)/2;
            }
            //To break if it is smaller than it is parent since it is in the right position
            else
                break;
        }
    }

    public void sink(int index){

        //Loop for the node until it have no children
        while(size>= index *2+1){

            //To get the left and right children index
            int left = 2 * index + 1;
            int right = left + 1;

            //To get the smaller child index
            int min = left;
            if (right < size && heap[right].compareTo(heap[left]) < 0)
                min = right;

            //To swap the smaller child with the parent if it is smaller than it parent, otherwise end the loop
            if (heap[index].compareTo(heap[min]) > 0){
                swap(heap,index,min);
                index = min;
            }else
                break;

        }
    }

    private static <T> void swap(T[] array,int i,int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void print(){
        for (int i = 0; i < size; i++)
            System.out.print(heap[i]+" ");
    }

}
