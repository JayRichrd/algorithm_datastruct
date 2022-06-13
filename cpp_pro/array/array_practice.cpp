//
// Created by cainjiang on 2022/4/27.
//
#include <iostream>
#include "array.hpp"
#include <unordered_map>
#include <algorithm>
#include <cstdlib>

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

    vector<vector<int>> ArrayPractice::threeNumSum(vector<int> nums) {
        vector<vector<int>> results = {};
        if (nums.size() < 3) {
            return results;
        }
        // sort firstly
        sort(nums.begin(), nums.end());
        int len = nums.size();
        for (int i = 0; i < len; ++i) {
            int firstNum = nums[i];
            if (firstNum > 0) {
                break;
            }
            // skip duplicate num
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int secondNumIndex = i + 1, thirdNumIndex = len - 1;
            while (secondNumIndex < thirdNumIndex) {
                int temp = firstNum + nums[secondNumIndex] + nums[thirdNumIndex];
                if (temp == 0) {
                    vector<int> result = {firstNum, nums[secondNumIndex], nums[thirdNumIndex]};
                    results.push_back(result);
                    // skip duplicate num
                    while (secondNumIndex < thirdNumIndex && nums[secondNumIndex + 1] == nums[secondNumIndex]) {
                        secondNumIndex++;
                    }
                    while (secondNumIndex < thirdNumIndex && nums[thirdNumIndex - 1] == nums[thirdNumIndex]) {
                        secondNumIndex--;
                    }
                    secondNumIndex++;
                    thirdNumIndex--;
                } else if (temp < 0) {
                    secondNumIndex++;
                } else {
                    thirdNumIndex--;
                }
            }
        }
        return results;
    }

    void ArrayPractice::testThreeNumSum() {
        vector<int> nums = {-1, 0, 1, 2, -1, -4};
        auto results = threeNumSum(nums);
        cout << "the results: " << endl;
        for (const auto &result: results) {
            for (auto num: result) {
                cout << num << ", ";
            }
            cout << endl;
        }
    }

    int ArrayPractice::findFirstMissingNum(vector<int> nums) {
        if (nums.size() == 0) {
            return -1;
        }
        int len = nums.size();
        // the final result must belong to [1,len+1]
        int result = len + 1;
        // handle negative and zero
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = len + 1;
            }
        }
        // mark possible index
        for (int i = 0; i < len; i++) {
            int num = abs(nums[i]);
            if (num <= len) {
                nums[num - 1] = -abs(nums[num - 1]);
            }
        }
        // find index which num is first positive num
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return result;
    }

    void ArrayPractice::testFindFirstMissingNum() {
        vector<int> nums = {3, 4, -1, 1, 9};
        cout << "first positive missing number = " << findFirstMissingNum(nums) << endl;
    }
}