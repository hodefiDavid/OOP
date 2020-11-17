package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms {
    private graph g;

    public Graph_Algo() {
    }

    public Graph_Algo(graph g0) {
        this.g = g0;
    }


    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(graph g) {
        this.g = g;
    }

    /**
     * Compute a deep copy of this graph.
     *
     * @return
     */
    @Override
    public graph copy() {

        graph g1 = new Graph_DS();

        for (node_data i : g.getV()) {
            g1.addNode(new NodeData(i));
        }

        for (node_data i : g.getV()) {
            for (node_data j : i.getNi()) {
                g1.connect(i.getKey(), j.getKey());
            }
        }
        return g1;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume ubdirectional graph.
     *
     * @return
     */
    @Override

    public boolean isConnected() {

        if (this.g.getV().iterator().hasNext()) {
            node_data temp = this.g.getV().iterator().next();

        //initialize all the tags of the nodes to -1
            for (node_data i : g.getV()) {
                i.setTag(-1);
            }
        //because I set the "setDistance" function to get two nodes and the function stop when its get to the destination
        //I put the temp node twice so the function does not stop until it reach all the connected nodes
            setDistance(temp,temp);
        //now I check if at least one node not connected - if is not connected its tag remain -1
            for (node_data i : this.g.getV())
                if (i.getTag() == -1) {
                    return false;
                }
        }

        return true;
    }

    /**
     * returns the length of the shortest path between src to dest
     * return -1 if the node not connected
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public int shortestPathDist(int src, int dest) {

        node_data Src = g.getNode(src);
        node_data Dest = g.getNode(dest);
        //if the dest is the src itself
        if (Src == Dest)
            return 0;

        //initialize the nodes tag
        for (node_data i : g.getV()) {
            i.setTag(-1);
        }


        setDistance(Src,Dest);

        return Dest.getTag();

    }



    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {

        ArrayList<node_data> ll = new ArrayList<>();

        int flag = shortestPathDist(src, dest);
        if (flag != -1) {
            node_data Src = this.g.getNode(src);
            node_data Dest = this.g.getNode(dest);

            return ShortPath(Dest, Src, ll, flag);
        }

        return ll;
    }



    /**
     *
     * change the tag to the distance between node_data to the rest of the graph nodes until it reach the destination
     * if the tag = -1 the nodes are not connected
     *
     * @@param node_data
     */
    private void setDistance(node_data n,node_data dest) {

        Queue<node_data> q = new LinkedList<>();

        n.setTag(0);
        q.add(n);

        while (!q.isEmpty()) {
            node_data temp = q.poll();
            for (node_data i : temp.getNi()) {
                if (i.getTag() == -1) {
                    q.add(i);
                    i.setTag(temp.getTag() + 1);
                    if(i==dest)
                        return;
                }
            }
        }
    }

    /**
     *
     *return list with the path from one node to the other
     *
     * @@param node_data src
     * @@param node_data dest
     * @@param ArrayList<node_data> ll
     * @@param int distance
     */
    private List<node_data> ShortPath(node_data dest, node_data src, ArrayList<node_data> ll, int distance) {
        //check if the nodes are even connected return an empty path if they dosnt connected
        int index = distance;
        Stack<node_data> stack = new Stack<>();
        stack.add(dest);

        while (index > 0) {
            for (node_data i : stack.peek().getNi()) {
                if (i.getTag() == index - 1) {
                    stack.add(i);
                    index--;
                    break;
                }
            }

        }
        ll.addAll(stack);

        return ll;
    }


    @Override
    public String toString() {
        return "Graph_Algo{" +
                "g=" + g.getV().toString() +
                '}';
    }
}




//package ex0;
//
//import java.util.*;
//
//public class Graph_Algo implements graph_algorithms {
//    private graph g;
//
//    public Graph_Algo() {
//    }
//
//    public Graph_Algo(graph g0) {
//        this.g = g0;
//    }
//
//
//    /**
//     * Init the graph on which this set of algorithms operates on.
//     *
//     * @param g
//     */
//    @Override
//    public void init(graph g) {
//        this.g = g;
//    }
//
//    /**
//     * Compute a deep copy of this graph.
//     *
//     * @return
//     */
//    @Override
//    public graph copy() {
//
//        graph g1 = new Graph_DS();
//
//        for (node_data i : g.getV()) {
//            g1.addNode(new NodeData(i));
//        }
//
//        for (node_data i : g.getV()) {
//            for (node_data j : i.getNi()) {
//                g1.connect(i.getKey(), j.getKey());
//            }
//        }
//        return g1;
//    }
//
//    /**
//     * Returns true if and only if (iff) there is a valid path from EVREY node to each
//     * other node. NOTE: assume ubdirectional graph.
//     *
//     * @return
//     */
//    @Override
//
//    public boolean isConnected() {
//
//        if (this.g.getV().iterator().hasNext()) {
//            node_data temp = this.g.getV().iterator().next();
//
//            //initialize all the tags of the nodes to -1
////            for (node_data i : g.getV()) {
////                i.setTag(-1);
////            }
//            //because I set the "setDistance" function to get two nodes and the function stop when its get to the destination
//            //I put the temp node twice so the function does not stop until it reach all the connected nodes
//            setDistance(temp,temp);
//            //now I check if at least one node not connected - if is not connected its tag remain -1
//            for (node_data i : this.g.getV())
//                if (i.getTag() == -1) {
//                    return false;
//                }
//        }
//
//        return true;
//    }
//
//    /**
//     * returns the length of the shortest path between src to dest
//     * return -1 if the node not connected
//     * @param src  - start node
//     * @param dest - end (target) node
//     * @return
//     */
//    @Override
//    public int shortestPathDist(int src, int dest) {
//
//        node_data Src = g.getNode(src);
//        node_data Dest = g.getNode(dest);
//        //if the dest is the src itself
//        if (Src == Dest)
//            return 0;
//
//        //initialize the nodes tag
//        for (node_data i : g.getV()) {
//            i.setTag(-1);
//        }
//
//
//        setDistance(Src,Dest);
//
//        return Dest.getTag();
//
//    }
//
//
//
//    /**
//     * returns the the shortest path between src to dest - as an ordered List of nodes:
//     * src--> n1-->n2-->...dest
//     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
//     *
//     * @param src  - start node
//     * @param dest - end (target) node
//     * @return
//     */
//    @Override
//    public List<node_data> shortestPath(int src, int dest) {
//        ArrayList<node_data> ll = new ArrayList<>();
//
//        int flag = shortestPathDist(src, dest);
//        if (flag != -1) {
//            node_data Src = this.g.getNode(src);
//            node_data Dest = this.g.getNode(dest);
//
//            return ShortPath(Dest, Src, ll, flag);
//        }
//
//        return ll;
//    }
//
//
//
//    /**
//     *
//     * change the tag to the distance between node_data to the rest of the graph nodes until it reach the destination
//     * if the tag = -1 the nodes are not connected
//     *
//     * @@param node_data
//     */
//    private void setDistance(node_data n,node_data dest) {
//        Queue<node_data> q = new LinkedList<>();
//
//        n.setTag(0);
//        q.add(n);
//
//        while (!q.isEmpty()) {
//            node_data temp = q.poll();
//            for (node_data i : temp.getNi()) {
//                if (i.getTag() == -1) {
//                    q.add(i);
//                    i.setTag(temp.getTag() + 1);
//                    if(i==dest)
//                        return;
//                }
//            }
//        }
//    }
//
//    /**
//     *
//     *return list with the path from one node to the other
//     *
//     * @@param node_data src
//     * @@param node_data dest
//     * @@param ArrayList<node_data> ll
//     * @@param int distance
//     */
//    private List<node_data> ShortPath(node_data dest, node_data src, ArrayList<node_data> ll, int distance) {
//        //check if the nodes are even connected return an empty path if they dosnt connected
//        int index = distance;
//        Stack<node_data> stack = new Stack<>();
//        stack.add(dest);
//
//        while (index > 0) {
//            for (node_data i : stack.peek().getNi()) {
//                if (i.getTag() == index - 1) {
//                    stack.add(i);
//                    index--;
//                    break;
//                }
//            }
//
//        }
//        ll.addAll(stack);
//
//        return ll;
//    }
//
//
//    @Override
//    public String toString() {
//        return "Graph_Algo{" +
//                "g=" + g.getV().toString() +
//                '}';
//    }
//}
//
//


