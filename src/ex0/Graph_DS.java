package ex0;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph_DS implements graph {

    private HashMap<Integer, node_data> graphNodes;
    private int edge_size;
    private int mode_count;

    public Graph_DS() {
        this.graphNodes = new HashMap<>();
        this.edge_size = 0;
        this.mode_count = 0;
    }

    /**
     * return the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        if (this.graphNodes.containsKey(key) == true) {
            return this.graphNodes.get(key);
        }
        return null;
    }


    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    public boolean hasEdge(int node1, int node2) {

        return getNode(node1).hasNi(node2);
    }


    /**
     * add a new node to the graph with the given node_data.
     * Note: this method should run in O(1) time.
     *
     * @param n
     */
    @Override
    public void addNode(node_data n) {
        if(!this.graphNodes.containsKey(n.getKey())){
        this.graphNodes.put(n.getKey(), n);
        this.mode_count++;
         }

    }

    /**
     * Connect an edge with weight w between node src to node dest.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the the method simply does nothing.
     *
     * @param node1
     * @param node2
     */
    @Override
    public void connect(int node1, int node2) {

        if (node1 == node2)
            return;

        node_data a = this.getNode(node1);
        node_data b = this.getNode(node2);
        if (b != null && a != null) {

            if (!this.hasEdge(node1,node2)) {

                a.addNi(b);
                b.addNi(a);
                this.mode_count++;
                this.edge_size++;
            }
        }

    }

    /**
     * This method return a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV() {
        return this.graphNodes.values();
    }

    /**
     * This method return a collection of  the
     * collection representing all the nodes connected to node_id
     * Note: this method should run in O(1) time.
     *
     * @param node_id
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV(int node_id) {
        return this.graphNodes.get(node_id).getNi();
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_data removeNode(int key) {

        node_data temp = getNode(key);
        if (temp != null) {

            this.graphNodes.remove(key);

            LinkedList<node_data> ll = new LinkedList<>(temp.getNi());

            for (node_data i : ll) {
                edge_size--;
                mode_count++;
                i.removeNode(temp);
                temp.removeNode(i);
            }

        }

        return temp;
    }

    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (this.graphNodes.containsKey(node1) && this.graphNodes.containsKey(node2)) {
            node_data a = this.getNode(node1);
            node_data b = this.getNode(node2);
            if (this.hasEdge(node1,node2)) {

                a.getNi().remove(b);
                b.getNi().remove(a);
                edge_size--;
                mode_count++;
          }
        }

    }

    /**
     * return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return this.graphNodes.size();
    }

    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int edgeSize() {

        return edge_size;
    }


    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     *
     * @return
     */
    @Override
    public int getMC() {
        return this.mode_count;
    }

    @Override
    public String toString() {
        String s = "";
        for (node_data i : this.graphNodes.values())
            s += i + ": " + i.getNi() + " | ";
        return "Graph{" +
                "\ngraphNodes= " + graphNodes +
                "\nneighborNodes " + s +
                '}';
    }

}

