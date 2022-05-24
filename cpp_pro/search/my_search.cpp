//
// Created by cainjiang on 2022/5/24.
//
#pragma clang diagnostic push
#pragma ide diagnostic ignored "cppcoreguidelines-narrowing-conversions"
#pragma ide diagnostic ignored "misc-no-recursion"

#include "my_search.hpp"
#include <iostream>

namespace search_practice {
    using namespace std;

    int MySearch::binary_search_by_iteration(const std::vector<int> &nums, int k) {
        int low = 0;
        int high = nums.size() - 1;
        while (low <= high) {
            // avoid low+high is out of int max
            int mid = low + ((high - low) >> 1);
            // adjust the low and high
            if (nums[mid] == k) {
                return mid;
            } else if (nums[mid] < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    int MySearch::binary_search_by_recursion(const vector<int> &nums, int k) {
        return binary_search_do_recursion(nums, k, 0, nums.size() - 1);
    }

    int MySearch::binary_search_do_recursion(const vector<int> &nums, int k, int low, int high) {
        if (low > high) {
            return -1;
        }
        // avoid low+high is out of int max
        int mid = low + ((high - low) >> 1);
        if (nums[mid] == k) {
            return mid;
        } else if (nums[mid] < k) {
            return binary_search_do_recursion(nums, k, mid + 1, high);
        } else {
            return binary_search_do_recursion(nums, k, low, mid - 1);
        }
    }

    void MySearch::test_binary_search() {
        vector<int> nums = {1, 3, 5, 9, 11, 24, 50, 66};
        int k = 66;
        cout << "nums: ";
        for (auto num: nums) {
            cout << num << ", ";
        }
        cout << endl;
        cout << k << " index in nums: " << binary_search_by_iteration(nums, k) << endl;
        cout << k << " index in nums: " << binary_search_by_recursion(nums, k) << endl;
    }

    int MySearch::search_insert(const vector<int> &nums, int k) {
        int low = 0;
        int high = nums.size() - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] >= k) {
                if (mid == 0 || nums[mid - 1] <= k) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    void MySearch::test_search_insert() {
        vector<int> nums = {1, 3, 5, 9, 11, 24, 50, 66};
        int k = 30;
        cout << "nums: ";
        for (auto num: nums) {
            cout << num << ", ";
        }
        cout << endl;
        cout << k << " index in nums: " << search_insert(nums, k) << endl;
    }

    int MySearch::my_sqrt(int k) {
        if (k <= 1) {
            return 0;
        }
        int low = 1;
        int high = k;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (mid * mid >= k) {
                if (mid * mid == k) {
                    return mid;
                } else if ((mid - 1) * (mid - 1) < k) {
                    return mid - 1;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return 0;
    }

    void MySearch::test_my_sqrt() {
        int k = 8;
        cout << k << "'s sqt = " << my_sqrt(k) << endl;
        k = 4;
        cout << k << "'s sqt = " << my_sqrt(k) << endl;
    }
}

#pragma clang diagnostic pop