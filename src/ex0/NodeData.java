package ex0;

import java.util.Collection;
import java.util.HashMap;


public class NodeData implements node_data{

    private static int masterKey=0;
    private int key;
    private HashMap<Integer, node_data> neighborNodes;
    private int Tag;
    private String remark;

    public NodeData(){
        this.key=masterKey;
        masterKey++;
        this.neighborNodes = new HashMap<>();
        this.remark="";
        this.setTag(-1);
    }

//copying without neighbors
public NodeData(node_data n){
        this.key=n.getKey();
        this.remark=n.getInfo();
        this.setTag(n.getTag());
        this.neighborNodes = new HashMap<>();
}


    /**
     * Return the key (id) associated with this node.
     * Note: each node_data should have a unique key.
     *
     * @return
     */
    @Override
    public int getKey() {

        return this.key;
    }

    /**
     * This method returns a collection with all the Neighbor nodes of this node_data
     */
    @Override
    public Collection<node_data> getNi() {
        return neighborNodes.values();
    }

    /**
     * return true iff this<==>key are adjacent, as an eֳdge between them.
     *
     * @param key
     * @return
     */
    @Override
    public boolean hasNi(int key) {
        return this.neighborNodes.containsKey(key);
    }

    /**
     * This method adds the node_data (t) to this node_data.
     *
     * @param t
     */
    @Override
    public void addNi(node_data t) {
    if(!this.neighborNodes.containsKey(t.getKey())){
        this.neighborNodes.put(t.getKey(),t);
    }
    }

    /**
     * Removes the edge this-key,
     *
     * @param node
     */
    @Override
    public void removeNode(node_data node) {
    if(this.neighborNodes.containsKey(node.getKey())){
        this.neighborNodes.remove(node.getKey());
    }
    }

    /**
     * return the remark (meta data) associated with this node.
     *
     * @return
     */
    @Override
    public String getInfo() {
        return this.remark;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     *
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this.remark=s;
    }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     *
     * @return
     */
    @Override
    public int getTag() {
        return this.Tag;
    }

    /**
     * Allow setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     *
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.Tag=t;

    }

    @Override
    public String toString() {
       return  "{" +key+"}";
    }
}
