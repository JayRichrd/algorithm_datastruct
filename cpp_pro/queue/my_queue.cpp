//
// Created by cainjiang on 2022/5/13.
//
#include "my_queue.hpp"
#include <queue>
#include <deque>
#include <utility>
#include <iostream>

namespace queue_practice {
    using namespace std;

    vector<int> MyQueue::max_sliding_window1(const vector<int> &nums, const int k) {
        size_t size = nums.size();
        vector<int> result;
        if (size < k) {
            return result;
        }

        priority_queue<pair<int, int>> max_top_heap;
        for (int i = 0; i < k; ++i) {
            max_top_heap.emplace(nums[i], i);
        }
        result.push_back(max_top_heap.top().first);

        for (int i = k; i < size; ++i) {
            max_top_heap.emplace(nums[i], i);
            /**
             * in case the top is out of window
             */
            while (!max_top_heap.empty() && max_top_heap.top().second <= i - k) {
                max_top_heap.pop();
            }
            if (max_top_heap.empty()) {
                continue;
            }
            result.push_back(max_top_heap.top().first);
        }
        return result;
    }

    vector<int> MyQueue::max_sliding_window2(const vector<int> &nums, const int k) {
        size_t size = nums.size();
        vector<int> result;
        if (size < k) {
            return result;
        }

        deque<int> descending_q;
        for (int i = 0; i < k; ++i) {
            /**
             * be sure the descending_q is descending
             */
            while (!descending_q.empty() && nums[i] >= nums[descending_q.back()]) {
                descending_q.pop_back();
            }
            descending_q.push_back(i);
        }
        result.push_back(nums[descending_q.front()]);

        for (int i = k; i < size; ++i) {
            /**
             * be sure the descending_q is descending
             */
            while (!descending_q.empty() && nums[i] >= nums[descending_q.back()]) {
                descending_q.pop_back();
            }
            descending_q.push_back(i);
            /**
             * in case the top is out of window
             */
            while (descending_q.front() <= i - k) {
                descending_q.pop_front();
            }
            if (descending_q.empty()) {
                continue;
            }
            result.push_back(nums[descending_q.front()]);
        }
        return result;
    }

    void MyQueue::test_max_sliding_window() {
        vector<int> nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        cout << "nums1: ";
        for (int num: nums1) {
            cout << num << ", ";
        }
        cout << endl;
        vector<int> max_sliding_window_vector1 = max_sliding_window1(nums1, 3);
        vector<int> max_sliding_window_vector2 = max_sliding_window2(nums1, 3);
        cout << "max sliding window1: ";
        for (int num: max_sliding_window_vector1) {
            cout << num << ", ";
        }
        cout << endl;
        cout << "max sliding window2: ";
        for (int num: max_sliding_window_vector2) {
            cout << num << ", ";
        }
        cout << endl;
    }
}
