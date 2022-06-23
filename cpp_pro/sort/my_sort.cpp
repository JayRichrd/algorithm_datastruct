//
// Created by cainjiang on 2022/5/23.
//
#pragma clang diagnostic push
#pragma ide diagnostic ignored "misc-no-recursion"
#pragma ide diagnostic ignored "cppcoreguidelines-narrowing-conversions"
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"

#include "my_sort.hpp"
#include <iostream>

namespace sort_practice {
    using namespace std;

    void MySort::bubble_sort(vector<int> &nums) {
        if (nums.empty()) return;
        int size = nums.size();
        // ascending
        for (int i = 0; i < size; ++i) {
            bool flag = false;
            for (int j = 0; j < size - i - 1; ++j) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = true;
                }
            }
            // if no exchange, wo should finish immediately
            if (!flag) {
                break;
            }
        }
    }

    void MySort::insert_sort(vector<int> &nums) {
        if (nums.empty()) return;
        int size = nums.size();
        // ascending
        for (int i = 1; i < size; ++i) {
            int value = nums[i];
            int j = i - 1;
            // compare each other from back to front
            while (j >= 0) {
                // find target place
                if (nums[j] < value) {
                    break;
                }
                // move element to back
                nums[j + 1] = nums[j];
                j--;
            }
            // insert
            nums[j + 1] = value;
        }
    }

    void MySort::select_sort(vector<int> &nums) {
        if (nums.empty()) return;
        int size = nums.size();
        for (int i = 0; i < size; ++i) {
            int minIndex = i;
            // find the min in back part
            for (int j = i + 1; j < size; ++j) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            // exchange
            if (minIndex != i) {
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
    }

    void MySort::merge_sort(vector<int> &nums) {
        if (nums.empty()) return;
        merge_sort_recursion(nums, 0, nums.size() - 1);
    }

    void MySort::merge_sort_recursion(vector<int> &nums, int start, int end) {
        if (start >= end) {
            return;
        }

        // sort recursive
        int mid = (start + end) >> 1;
        merge_sort_recursion(nums, start, mid);
        merge_sort_recursion(nums, mid + 1, end);

        // merge the left part and right part
        int temp[end - start + 1];
        int count = 0;
        int i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            if (nums[i] < nums[j]) {
                temp[count++] = nums[i++];
            } else {
                temp[count++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[count++] = nums[i++];
        }
        while (j <= end) {
            temp[count++] = nums[j++];
        }

        count = 0;
        for (int k = start; k <= end; ++k) {
            nums[k] = temp[count++];
        }
    }

    void MySort::quick_sort(vector<int> &nums) {
        if (nums.empty()) return;
        quick_sort_recursion(nums, 0, nums.size() - 1);
    }

    void MySort::quick_sort_recursion(vector<int> &nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIndex = quick_sort_partition(nums, left, right);
        // continue
        quick_sort_recursion(nums, left, pivotIndex - 1);
        quick_sort_recursion(nums, pivotIndex + 1, right);
    }

    int MySort::quick_sort_partition(vector<int> &nums, int left, int right) {
        // use the last element as pivot
        int pivot = nums[right];
        // i indicate the next place to exchange
        int i = left;
        for (int j = i; j < right; j++) {
            // find the element that is less than the pivot
            if (nums[j] < pivot) {
                quick_sort_swap(nums, i, j);
                i++;
            }
        }
        quick_sort_swap(nums, i, right);
        return i;
    }

    void MySort::test_quick_sort() {
        vector<int> nums = {2, 8, 4, 1, 3, 5, 6, 6, 11, 3, 9, 8};
        cout << "nums: ";
        for (auto num: nums) {
            cout << num << ", ";
        }
        cout << endl;
        quick_sort(nums);
        cout << "nums after quick sort: ";
        for (auto num: nums) {
            cout << num << ", ";
        }
    }

    void MySort::quick_sort_swap(vector<int> &nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    int MySort::find_kth_larger(vector<int> nums, int k) {
        if (nums.size() < k) {
            return INTMAX_MIN;
        }
        return find_kth_larger_recursion(nums, 0, nums.size() - 1, k);
    }

    int MySort::find_kth_larger_recursion(vector<int> &nums, int left, int right, int k) {
        if (left >= right) {
            return INTMAX_MIN;
        }
        int pivot = find_kth_larger_partition(nums, left, right);
        if (pivot == k - 1) {
            // found
            return nums[pivot];
        } else if (pivot < k - 1) {
            // kth larger should be in the right
            return find_kth_larger_recursion(nums, pivot + 1, right, k);
        } else {
            // kth larger should be in the left
            return find_kth_larger_recursion(nums, left, pivot - 1, k);
        }
    }

    int MySort::find_kth_larger_partition(vector<int> &nums, int left, int right) {
        // descending
        int pivot = nums[right];
        int i = left;
        for (int j = i; j < right; ++j) {
            if (nums[j] > pivot) {
                quick_sort_swap(nums, i, j);
                i++;
            }
        }
        quick_sort_swap(nums, i, right);
        return i;
    }

    void MySort::test_kth_larger() {
        vector<int> nums = {2, 8, 4, 1, 3, 5, 6, 6, 11, 3, 9, 8};
        int k = 5;
        cout << "nums: ";
        for (auto num: nums) {
            cout << num << ", ";
        }

        cout << endl;

        int kth_larger = find_kth_larger(nums, k);
        cout << k << "th larger num = " << kth_larger;

        cout << endl;

        quick_sort(nums);
        cout << "nums after quick sort: ";
        for (auto num: nums) {
            cout << num << ", ";
        }
    }
}

#pragma clang diagnostic pop