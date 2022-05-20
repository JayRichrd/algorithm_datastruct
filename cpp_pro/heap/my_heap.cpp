//
// Created by cainjiang on 2022/5/20.
//
#pragma clang diagnostic push
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"

#include "my_heap.hpp"
#include <iostream>
#include <queue>

namespace heap_practice {
    using namespace std;

    MaxHeap::MaxHeap(int capacity) {
        this->capacity = capacity;
        this->nums = new int[capacity + 1];
        this->count = 0;
    }

    MaxHeap::~MaxHeap() {
        delete[] nums;
        count = 0;
    }

    bool MaxHeap::insert(int data) {
        // filled
        if (count >= capacity) {
            return false;
        }
        // index 0 no element
        count++;
        nums[count] = data;
        int curIndex = count;
        /**
         * from down to up heaping
         * only compare child and parent node
         */
        while (curIndex / 2 > 0 && nums[curIndex] > nums[curIndex / 2]) {
            swap(this->nums, curIndex / 2, curIndex);
            curIndex >>= 1;
        }
        return true;
    }

    bool MaxHeap::pop() {
        if (count == 0) return false;
        nums[1] = nums[count];
        count--;
        heapify(this->nums, 1, count);
        return true;
    }

    void MaxHeap::swap(int nums[], int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void MaxHeap::heapSort(int nums[], int count) {
        /**
         * from bottom to top build heap tree
         * Time complexity: O(n)
         */
        for (int i = count / 2; i >= 1; --i) {
            heapify(nums, i, count);
        }
        /**
         * move element to sort
         */
        int j = count;
        while (j > 1) {
            swap(nums, 1, j);
            // be careful, minus firstly then heapify
            j--;
            heapify(nums, 1, j);
        }
    }

    void MaxHeap::heapify(int nums[], int i, int count) {
        while (true) {
            /**
             * from top to bottom heaping
             * compare current node with its two child node, and find the max
             */
            int maxIndex = i;
            if (i * 2 <= count && nums[i] < nums[i * 2]) {
                maxIndex = i * 2;
            }
            if (i * 2 + 1 <= count && nums[i * 2 + 1] > nums[maxIndex]) {
                maxIndex = i * 2 + 1;
            }
            /**
             * because down subtree is maxheap tree
             * if maxIndex = curIndex note that up subtree is also maxheap tree
             * so finish recycle heaping now
             */
            if (maxIndex == i) {
                break;
            }
            swap(nums, i, maxIndex);
            i = maxIndex;
        }
    }

    void MaxHeap::test_heap_sort() {
        int nums[10];
        nums[1] = 7;
        nums[2] = 5;
        nums[3] = 19;
        nums[4] = 16;
        nums[5] = 4;
        nums[6] = 1;
        nums[7] = 20;
        nums[8] = 13;
        nums[9] = 8;
        heapSort(nums, 9);
        cout << "sorted result: ";
        for (int i = 1; i < 10; ++i) {
            cout << nums[i] << ", ";
        }
    }

    vector<int> MaxHeap::top_k(vector<int> nums, int k) {
        if (nums.size() <= k) {
            return nums;
        }

        // init vector with capacity
        vector<int> result = vector<int>(k, 0);
        if (k <= 0) {
            return result;
        }

        priority_queue<int, vector<int>, greater<int>> minHeap;
        // fill min heap firstly
        for (int i = 0; i < k; ++i) {
            minHeap.push(nums[i]);
        }

        for (int i = k; i < nums.size(); ++i) {
            if (nums[i] > minHeap.top()) {
                minHeap.pop();
                minHeap.push(nums[i]);
            }
        }

        for (int i = 0; i < k; ++i) {
            //dose not assign num, can not use []
            //result.push_back(minHeap.top());
            result[i] = minHeap.top();
            minHeap.pop();
        }

        return result;
    }

    void MaxHeap::test_top_k() {
        vector<int> nums = {4, 5, 1, 6, 2, 7, 3, 8};
        cout << "top 3: ";
        vector<int> topK = top_k(nums, 3);
        for (auto i: topK) {
            cout << i << ", ";
        }
    }
}

#pragma clang diagnostic pop