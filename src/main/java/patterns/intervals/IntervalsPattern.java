package patterns.intervals;

import java.util.*;

public class IntervalsPattern {
    /*
     * This pattern is used in NeetCode 150 problems like:
     * 1. Merge Intervals
     * 2. Insert Interval
     * 3. Non-overlapping Intervals
     * 4. Meeting Rooms II
     */
    
    // Example 1: Merge Intervals (LeetCode 56)
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> result = new ArrayList<>();
        int[] currentInterval = intervals[0];
        result.add(currentInterval);
        
        for (int[] interval : intervals) {
            if (interval[0] <= currentInterval[1]) {
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else {
                currentInterval = interval;
                result.add(currentInterval);
            }
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    // Example 2: Insert Interval (LeetCode 57)
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        
        // Add all intervals before newInterval
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // Merge overlapping intervals
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        
        // Add remaining intervals
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    // Example 3: Non-overlapping Intervals (LeetCode 435)
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) return 0;
        
        // Sort by end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        int nonOverlap = 1;
        int end = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                nonOverlap++;
                end = intervals[i][1];
            }
        }
        
        return intervals.length - nonOverlap;
    }
    
    // Example 4: Meeting Rooms II (LeetCode 253)
    public static int minMeetingRooms(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        
        Arrays.sort(start);
        Arrays.sort(end);
        
        int rooms = 0;
        int maxRooms = 0;
        int startPtr = 0;
        int endPtr = 0;
        
        while (startPtr < intervals.length) {
            if (start[startPtr] < end[endPtr]) {
                rooms++;
                startPtr++;
            } else {
                rooms--;
                endPtr++;
            }
            maxRooms = Math.max(maxRooms, rooms);
        }
        
        return maxRooms;
    }
    
    public static void main(String[] args) {
        // Test Merge Intervals
        int[][] intervals1 = {{1,3}, {2,6}, {8,10}, {15,18}};
        System.out.println("Merged intervals: " + 
                         Arrays.deepToString(merge(intervals1)));
        
        // Test Insert Interval
        int[][] intervals2 = {{1,3}, {6,9}};
        int[] newInterval = {2,5};
        System.out.println("After inserting interval: " + 
                         Arrays.deepToString(insert(intervals2, newInterval)));
        
        // Test Non-overlapping Intervals
        int[][] intervals3 = {{1,2}, {2,3}, {3,4}, {1,3}};
        System.out.println("Minimum intervals to remove: " + 
                         eraseOverlapIntervals(intervals3));
        
        // Test Meeting Rooms II
        int[][] intervals4 = {{0,30}, {5,10}, {15,20}};
        System.out.println("Minimum meeting rooms required: " + 
                         minMeetingRooms(intervals4));
    }
}