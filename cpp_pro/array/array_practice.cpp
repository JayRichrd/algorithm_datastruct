//
// Created by cainjiang on 2022/4/27.
//
#include <iostream>
#include "array.hpp"
#include <unordered_map>

namespace array_practice {
    using namespace std;

    void ArrayPractice::merge_sorted_array(int array1[], int n, const int array2[], int m) {
        // sum of array1 and array2
        int sum = n + m;
        for (int i = n - 1, j = m - 1; i >= 0 && j >= 0;) {
            if (i >= 0 && j >= 0) {// array1 and array2 have element to compare and move
                if (array1[i] >= array2[j]) {
                    array1[--sum] = array1[i];
                    i--;
                } else {
                    array1[--sum] = array2[j];
                    j--;
                }
            } else if (i >= 0) {// just remain array1 element to move
                array1[--sum] = array1[i];
                i--;
            } else if (j >= 0) {// just remain array2 element to move
                array1[--sum] = array2[j];
                j--;
            }
        }
    }

    void ArrayPractice::test_merge_sorted_array() {
        int array2[] = {3, 6, 8};
        int array1[] = {1, 6, 7, 0, 0, 0};

        cout << "before:" << endl;
        for (auto element: array1) {
            cout << element << ", ";
        }

        cout << endl << "----------------------------------------------------------------------------------" << endl;

        merge_sorted_array(array1, 3, array2, 3);
        cout << "after:" << endl;
        for (auto element: array1) {
            cout << element << ", ";
        }
    }

    int ArrayPractice::majorElement1(vector<int> &nums) {
        /**
         * record every element's top
         */
        unordered_map<int, int> counts;
        int major = 0, count = 0;
        for (int num: nums) {
            ++counts[num];
            // find the max top
            if (counts[num] > count) {
                count = counts[num];
                major = num;
            }
        }
        return major;
    }

    int ArrayPractice::majorElement2(vector<int> &nums) {
        int count = 0, candidate = nums[0];
        for (int num: nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    void ArrayPractice::test_major_element() {
        vector<int> nums;
        nums.push_back(2);
        nums.push_back(2);
        nums.push_back(1);
        nums.push_back(1);
        nums.push_back(1);
        nums.push_back(2);
        nums.push_back(2);
        cout << "data: ";
        for (int num: nums) {
            cout << num << ", ";
        }
        cout << endl;
        int major1 = majorElement1(nums);
        int major2 = majorElement2(nums);
        cout << "major1: " << major1 << ", major2: " << major2 << endl;
    }
}