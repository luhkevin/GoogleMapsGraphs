PROBLEM 2:
The data structures created were all hashmaps:
"adjList" maps from points to their adjacent points (i.e. intersections), which is necessary to find find neighboring nodes in BFS and ShortestPath.

"edgeWeight" is necessary for retreiving the weight of each edge--useful in ShortestPath

"idStreet" is necessary in MinSpanningTree, when we need to order specific streets

"pointsToEdge" is used in ShortestPath in finding an edge, given the two endpoints of an edge. In this implementation of Dijkstra's, we search by neighboring vertices, so we *only* have vertices to work with, and not edges. 

Finally, "streetList" and "connected" are used in minTurns, whose implementation will be explained later

HashMaps were used because it allowed easy and efficient (~O(1)) access to any element, as long as you knew what the key was. 

PROBLEM 3:
We first obtain the set of all vertices from "adjList," which only takes O(n) time to create and run through. Next, we start adding vertices to our priority queue and relax its adjacent edges. 

In the "relax" function, we must access the neighbors of each node and the edges going to those neighbors. Thus, we have O(m + n) time. 

However, after the relax, we have reorder the vertices. Since insertion and deletion in a priority queue takes log(n) time, and we have to perform this as part of the relax method, after traversing the edges and accessing the neighbors of each node in our MinHeap. Thus, our time increases to O( (m +n) log (n)).

The bottleneck of Dijkstra's Algorithim is finding the minimum value after each relax. In order to reduce this bottleneck and ensure that our algorithm runs in
O((m+n) log n) time, we use a priority queue/binary min heap data structure to store our vertices. In the priority queue, maintaining the smallest element at the top of the heap takes O(log(n)) time, and extracting it thus takes only constant time. So, as explained above, we have a total of O((m+n) log (n)) time.

In actuality, we must also add in O(n) from getting the set of vertices, and O(n) for iterating through the hashmap (backTrace) to actually obtain the List of intersections, but O((m+n) log (n)) dominates O(n). 


Implementation Details:
As explained before, this implementation of Dijkstra's Algorithm starts by enqueuing a source node onto a priority queue. It then dequeus the priority queue's min element and adds all its neighbors onto the queue, and relaxes the edges. Then, the nodes are reordered so that the min element is at the top of the queue, and the process is repeated. 

In addition, each node's in the priority queue is mapped to the last node popped off of the priority queue. In generating the actual path list, we simply iterate this hashmap, starting at the destination node, and add each mapped value to a linked list.

Comparison in the priority queue is accomplished with a wrapper class for Intersection (IntersectionX). If an intersection's (i.e. point's) cost is larger than another's, then it is lower in the queue.


PROBLEM 4:

In this implementation, we use Sedgewick's UnionFind and Kruskal algorithm to find the MST. We get a list of streets (i.e. street IDs) and order them with a priority queue. Then, we add each edge to a set, which will contain our MST. In order to check for cycles, we use Sedgewick's UnionFind algorithm. 

This keeps our vertices in two disjoint sets by 
UnionFind prevents cycles by putting points into disjoint subsets with one another. E.g., two points are checked for containment in the same set with the "find" method. If they were, then adjoining an edge between them would create a cycle, since the two points in question--being in the same set--would already have been connected. If they aren't, then the edge is selected, and union merges those two elements into the same set.

In this implementation of UnionFind, elements are stored in arrays through their index, and a representative element--the value at an index--is chosen for each disjoint subset. If two elements are merged, say element "0" and element "1", then we set array[0] = array[1] = x, where x is the representative element for both 0 and 1. 

However, we can't exactly have points of intersection be array indices. Thus, in order to associate array indices with points of an intersection, we use a hashmap that maps points to integers.  


StreetX extends Street in this implementation in order to compare streets in our priority queue (An order is placed on distance/length of a street). 

The runtime of this algorithm is O(m log n) because we must sort the edges in a priority queue in order to extract the minimum edge, and since we are sorting each edge, it takes O(m log n) time.


PROBLEM 5:
A standard queue implementation of BFS is used here. The source node is placed into the queue and the queue pops off the head and the head is marked by placed it into the set "marked". Then the removed node's neighbors are appended to the queue, and each neighbor is first checked to see if they are marked. If not, then the neighbors of that neighbor is appended onto the queue and that neighbor is removed and added to a buffer.

After each "level" of neighbors are checked, a hashmap is created with a map from the original head of the queue to the buffer. This is repeated until the queue is empty--which means no more neighbors have been added, so the entire graph has been traversed.


PROBLEM 6:
Min turns starts by obtainin a graph from GraphMaker that maps each street to its connecting, or neighboring streets (or more specifically, street blocks to connecting street blocks). 

Then, it runs a bfs traversal until it finds the street block that contains the destination point in question. During the bfs traversal, a hashmap is created that maps an neighboring street to the street searched from (i.e., in the reverse of the direction of the search). 

After the destination point has been reached, that path of the bfs stops, and the rest of the paths continue to search until they reach the destination point. This is to ensure that all paths from the destination point to the source are considered.

Next, each path is traversed from the destination point back to the source point. Again, instead of traversing points, we traverse by edges--or street blocks. A counter is created for each path, which increments if street names are different from one mapped value to the next. A standard algorithm to find the minimum of these counters is used, and that value is returned.



URL for maps: http://www.seas.upenn.edu/~haolinlu/

