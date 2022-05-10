//
// Created by cainjiang on 2022/5/9.
//

#ifndef CPP_PRO_MY_QUEUE_HPP
#define CPP_PRO_MY_QUEUE_HPP

#include "../linked_list/linked_list.hpp"

namespace queue_practice {
    /**
     * queue by array
     * @tparam T
     */
    template<typename T>
    class ArrayQueue {
    private:
        // size of array
        int size;
        // the head of queue
        int head = 0;
        // the tail of queue
        int tail = 0;
        // the pointer of array
        T *items;
    public:
        explicit ArrayQueue(int size);

        ~ArrayQueue();

        /**
         * enqueue the element into queue tail
         * @param item the element which to be enqueued
         * @return
         */
        bool enqueue(T item);

        /**
         * get element from queue head
         * @return
         */
        T dequeue();
    };

    template<typename T>
    ArrayQueue<T>::ArrayQueue(int size) {
        this->size = size;
        this->head = 0;
        this->tail = 0;
        items = new T[size];
    }

    template<typename T>
    ArrayQueue<T>::~ArrayQueue() {
        delete[] items;
    }

    template<typename T>
    bool ArrayQueue<T>::enqueue(T item) {
        if (tail == size) {
            if (head == 0) {
                return false;
            }
            /**
             * move array's valid element to head only when tail grow up to size
             */
            for (int i = head; i < tail; ++i) {
                items[i - head] = item[i];
            }
            tail -= head;
            head = 0;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    template<typename T>
    T ArrayQueue<T>::dequeue() {
        if (head == tail) {
            return nullptr;
        }
        T item = items[head];
        head++;
        return item;
    }

    //----------------------------------------linked list queue--------------------------------------------------------
    /**
     * queue by linked list
     * @tparam T
     */
    template<typename T>
    class LinkedListQueue {
    private:
        linked_list::node<T> *head = nullptr;
        linked_list::node<T> *tail = nullptr;
    public:
        bool enqueue(linked_list::node<T> *item);

        linked_list::node<T> *dequeue();
    };

    /**
    * enqueue the element into queue tail
    * @param item the element which to be enqueued
    * @return
    */
    template<typename T>
    bool LinkedListQueue<T>::enqueue(linked_list::node<T> *item) {
        item->next = nullptr;
        if (head == nullptr) {
            head = item;
            tail = item;
            return true;
        }
        tail->next = item;
        tail = item;
        return true;
    }

    /**
    * get element from queue head
    * @return
    */
    template<typename T>
    linked_list::node<T> *LinkedListQueue<T>::dequeue() {
        if (head == nullptr) {
            return nullptr;
        }
        linked_list::node<T> *item = head;
        head = head->next;
        if (head == nullptr) {
            tail = nullptr;
        }
        item->next = nullptr;
        return item;
    }

    //----------------------------------------circle queue-------------------------------------------------------------
    /**
     * circle queue by array
     * @tparam T
     */
    template<typename T>
    class ArrayCircleQueue {
    private:
        int capacity;
        int head = 0;
        // tail position is always empty
        int tail = 0;
        T *items;
    public:
        explicit ArrayCircleQueue(int capacity);

        ~ArrayCircleQueue();

        bool enqueue(T item);

        T dequeue();
    };

    template<typename T>
    ArrayCircleQueue<T>::ArrayCircleQueue(int capacity) {
        this->capacity = capacity;
        this->head = 0;
        this->tail = 0;
        items = new T[capacity];
    }

    template<typename T>
    ArrayCircleQueue<T>::~ArrayCircleQueue() {
        delete[] items;
    }

    template<typename T>
    bool ArrayCircleQueue<T>::enqueue(T item) {
        /**
         * queue is full
         */
        if ((tail + 1) % capacity == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % capacity;
        return true;
    }

    template<typename T>
    T ArrayCircleQueue<T>::dequeue() {
        /**
         * queue is empty
         */
        if (head == tail) {
            return nullptr;
        }
        T item = items[head];
        head = (head + 1) % capacity;
        return item;
    }
}
#endif //CPP_PRO_MY_QUEUE_HPP
