  
package ex1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest {
    private static Random _rnd = null;

    @Test
    void nodeSize() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(1);

        g.removeNode(2);
        g.removeNode(1);
        g.removeNode(1);
        int s = g.nodeSize();
        assertEquals(1,s);

    }

    @Test
    void edgeSize() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,3,3);
        g.connect(0,1,1);
        int e_size =  g.edgeSize();
        assertEquals(3, e_size);
        double w03 = g.getEdge(0,3);
        double w30 = g.getEdge(3,0);
        assertEquals(w03, w30);
        assertEquals(w03, 3);
    }

    @Test
    void getV() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,3,3);
        g.connect(0,1,1);
        Collection<node_info> v = g.getV();
        Iterator<node_info> iter = v.iterator();
        while (iter.hasNext()) {
            node_info n = iter.next();
            assertNotNull(n);
        }
    }

    @org.junit.Test(timeout = 5000)
    @Test
    public void testRuntime() {
        int v = 100000, e = v*10;
        weighted_graph g = graph_creator(v,e,1);
        int key = 1;
        Collection<node_info> ni1 = g.getV(1);
        double w = g.getEdge(key, ni1.iterator().next().getKey());
        double ex = 0.0632310507;
        assertEquals(w,ex,0.0001);
    }

    @Test
    void hasEdge() {
        int v = 10, e = v*(v-1)/2;
        weighted_graph g = graph_creator(v,e,1);
        for(int i=0;i<v;i++) {
            for(int j=i+1;j<v;j++) {
                boolean b = g.hasEdge(i,j);
                assertTrue(b);
                assertTrue(g.hasEdge(j,i));
            }
        }
    }

    @Test
    void connect() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,3,3);
        g.removeEdge(0,1);
        assertFalse(g.hasEdge(1,0));
        g.removeEdge(2,1);
        g.connect(0,1,1);
        double w = g.getEdge(1,0);
        assertEquals(w,1);
    }


    @Test
    void removeNode() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,3,3);
        g.removeNode(4);
        g.removeNode(0);
        assertFalse(g.hasEdge(1,0));
        int e = g.edgeSize();
        assertEquals(0,e);
        assertEquals(3,g.nodeSize());
    }

    @Test
    void removeEdge() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,3,3);
        g.removeEdge(0,3);
        double w = g.getEdge(0,3);
        assertEquals(w,-1);
    }


    ///////////////////////////////////
    /**
     * Generate a random graph with v_size nodes and e_size edges
     * @param v_size
     * @param e_size
     * @param seed
     * @return
     */
    public static weighted_graph graph_creator(int v_size, int e_size, int seed) {
        weighted_graph g = new WGraph_DS();
        _rnd = new Random(seed);
        for(int i=0;i<v_size;i++) {
            g.addNode(i);
        }
        // Iterator<node_data> itr = V.iterator(); // Iterator is a more elegant and generic way, but KIS is more important
        int[] nodes = nodes(g);
        while(g.edgeSize() < e_size) {
            int a = nextRnd(0,v_size);
            int b = nextRnd(0,v_size);
            int i = nodes[a];
            int j = nodes[b];
            double w = _rnd.nextDouble();
            g.connect(i,j, w);
        }
        return g;
    }
    private static int nextRnd(int min, int max) {
        double v = nextRnd(0.0+min, (double)max);
        int ans = (int)v;
        return ans;
    }
    private static double nextRnd(double min, double max) {
        double d = _rnd.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return ans;
    }
    /**
     * Simple method for returning an array with all the node_data of the graph,
     * Note: this should be using an Iterator<node_edge> to be fixed in Ex1
     * @param g
     * @return
     */
    private static int[] nodes(weighted_graph g) {
        int size = g.nodeSize();
        Collection<node_info> V = g.getV();
        node_info[] nodes = new node_info[size];
        V.toArray(nodes); // O(n) operation
        int[] ans = new int[size];
        for(int i=0;i<size;i++) {ans[i] = nodes[i].getKey();}
        Arrays.sort(ans);
        return ans;
    }
}
//
//
//package ex1;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.LinkedList;
//
//import org.junit.jupiter.api.Test;
//
//
//class WGraph_DSTest {
//	/**
//    *
//    *test for building a new weighted graph
//    *in the test I build a new weighted graph and added one node 
//    *the assert true check if the number of node equal to the number of node that I added
//    */
//	private static Random _rnd = null;
//	
//	private static int nextRnd(int min, int max) {
//        double v = nextRnd(0.0 + min, (double) max);
//        int ans = (int) v;
//        return ans;
//    }
//
//    private static double nextRnd(double min, double max) {
//        double d = _rnd.nextDouble();
//        double dx = max - min;
//        double ans = d * dx + min;
//        DecimalFormat df = new DecimalFormat("####0.00");
//        ans = Double.parseDouble(df.format(ans));
//        return ans;
//    }
//	
//	@Test
//	void testWGraph_DS() {
//		
//	}
//	@Test
//		  public void test_path() {
//		        WGraph_DS g1 = new WGraph_DS();
//		        weighted_graph_algorithms ga = new WGraph_Algo(g1);
//		        int n = 100000, path_size = 1000;
//		        for (int i = 0; i < n; i++) {
//		            g1.addNode(i);
//		        }
//		        _rnd = new Random(1);
//		        double w = 0.1, sum = 0;
//		        List<Integer> s = new LinkedList<>();
//		        while (s.size() != path_size) {
//		        	int temp =nextRnd(0, n);
//		        	if(!s.contains(temp)) {
//		            s.add(temp);}
//		        }
//		        Iterator<Integer> it = s.iterator();
//		        int prev = it.next(), start = prev, next = -1;
//		        System.out.print("Expected:\n(" + prev + ")");
//		        List<node_info> sp_expected = new LinkedList<>();
//		        sp_expected.add(g1.getNode(prev));
//		        while (it.hasNext()) {
//		            next = it.next();
//		            w = nextRnd(0, 1.5);
//		            sum += w;
//		            g1.connect(prev, next, w);
////		            System.out.println("G.add_edge(\"" + prev + "\", \"" + next + "\", weight=" + w + ")");
//		            System.out.print(" - " + w + " -> (" + next + ") ");
//		            sp_expected.add(g1.getNode(next));
//		            prev = next;
//		        }
//		        for (int i = 1; i < n*3; i++) {
//		            int a = nextRnd(0, n);
//		            int b = nextRnd(0, n);
//		            if (!g1.hasEdge(a, b)) {
//		                g1.connect(a, b, nextRnd(sum+1, sum*3));
////		                System.out.println("G.add_edge(\"" + a + "\", \"" + b + "\", weight=" + (8) + ")");
//		            }
//		        }
////		        System.out.println(g1);
////		        System.out.println("\nActual:");
////		        System.out.println(start+" "+next);
//		        List<node_info> sp_actual = ga.shortestPath(start, next);
//		        System.out.println();
//		        for (int i = 0; i < sp_actual.size() - 1; i++) {
//		            System.out.print( sp_actual.get(i) + " - " + g1.getEdge(sp_actual.get(i).getKey(), sp_actual.get(i + 1).getKey()) + " -> ");
//		        }
////		        System.out.print(sp_actual.get(sp_actual.size()-1) +"\n");
////		        System.out.println(g1);
////		        System.out.println(s);
////		        System.out.println(sp_expected);
////		        System.out.println(sp_actual);
//
//		        assertEquals(sp_expected, sp_actual);
//		        assertArrayEquals(sp_expected.toArray(), sp_actual.toArray());
//		        assertEquals(sum, ga.shortestPathDist(start, next));
//		        System.out.println();
//		    System.out.println(sum);
////		weighted_graph g = new WGraph_DS();
////		g.addNode(0);
////		System.out.println(g);
////		int size = g.nodeSize();
////		String error = "failed building a new weighted graph";
////		assertTrue(size==1,error);
//		
//	}
//	/**
//    *
//    *test for the function getNode
//    *in the test I build a new weighted graph and added one node 
//    *the assert true check if the key of the node equal to the number that I added
//    */
//	@Test
//	void testGetNode() {
//		weighted_graph g = new WGraph_DS();
//		g.addNode(1);
//		node_info temp = g.getNode(1);
//		String error = "getNode function dosent work properly";
//		assertTrue(temp.getKey()==1, error);
//	}
//	/**
//    *
//    *test for the function hasEdge
//    *in the test I build a new weighted graph and added a few nodes, then I connected them to each other 
//    *the assert true check if the node connected, and assert false check before I coonnect them
//    */
//	@Test
//	void testHasEdge() {
//		weighted_graph g = new WGraph_DS();
//		for (int i = 0; i < 10; i++) {
//			g.addNode(i);
//		}
//		String error = "hasEdge function dosent work properly";
//
//		assertFalse(g.hasEdge(0, 2), error);
//		assertFalse(g.hasEdge(2, 4), error);
//		assertFalse(g.hasEdge(6, 8), error);
//		assertFalse(g.hasEdge(9, 9), error);
//	//connect the nodes
//		for (int i = 0; i < g.nodeSize(); i++) {
//			for (int j = 0; j < 10; j++) {
//				g.connect(j, i, i*j);
//			}
//		}
//		assertTrue(g.hasEdge(0, 2), error);
//		assertTrue(g.hasEdge(2, 4), error);
//		assertTrue(g.hasEdge(6, 8), error);
//		//check that the node doesn't "connected" to itself 
//		assertFalse(g.hasEdge(9, 9), error);
//	}
//
//	/**
//    *
//    *test for the function GetEdge
//    *in the test I build a new weighted graph and added a few nodes, then I connected them to each other 
//    *the assert true check if the function return the right value
//    */
//	@Test
//	void testGetEdge() {
//		weighted_graph g = new WGraph_DS();
//		for (int i = 0; i < 10; i++) {
//			g.addNode(i);
//		}
//		String error = "getEdge function dosent work properly";
//
//		assertTrue(g.getEdge(0, 2)==-1, error);
//		assertTrue(g.getEdge(2, 4)==-1, error);
//		assertTrue(g.getEdge(6, 8)==-1, error);
//		assertTrue(g.getEdge(9, 9)==-1, error);
//	//connect the nodes
//		for (int i = 0; i < g.nodeSize(); i++) {
//			for (int j = 0; j < 10; j++) {
//				g.connect(j, i, 2.5);
//			}
//		}
//		assertTrue(g.getEdge(0, 2)==2.5, error);
//		assertTrue(g.getEdge(2, 4)==2.5, error);
//		assertTrue(g.getEdge(6, 8)==2.5, error);
//		assertFalse(g.getEdge(0, 2)==-1, error);
//		assertFalse(g.getEdge(2, 4)==-1, error);
//		assertFalse(g.getEdge(6, 8)==2, error);
//		assertTrue(g.getEdge(9, 9)==-1, error);
//	
//	}
//	/**
//    *
//    *test for the function AddNode
//    *in the test I build a new weighted graph and added a million nodes
//    *the assert true check if the function run in legitimate time run, and if all the nodes are added 
//    */
//	@Test
//	void testAddNode() {
//		String error = "AddNode function dosent work properly";
//
//        long start = new Date().getTime();
//       
//        weighted_graph Million = new WGraph_DS();
//        for (int i = 0; i < 1000000; i++) {
//        	Million.addNode(i);
//        }
//        long end = new Date().getTime();
//        double dt = (end-start)/1000.0;
//        assertTrue(Million.nodeSize()==1000000,error);
//        assertTrue(dt<5,error+" time fault");
//	}
//	/**
//    *
//    *test for the function Connect
//    *in the test I build a new weighted graph and added a 100 nodes
//    *the assert true check if the function run in legitimate time run, and if all the nodes are connected 
//    */
//	@Test
//	void testConnect() {
//		String error = "Connect function dosent work properly";
//
//        long start = new Date().getTime();
//       
//        weighted_graph g = new WGraph_DS();
//        for (int i = 0; i < 100; i++) {
//        	g.addNode(i);
//        }
//        for (int i = 0; i < g.nodeSize()-10; i++) {
//			for (int j = 0; j < 10; j++) {
//				g.connect(i, j, Math.random()*10);
//			}
//		}
//        
//        long end = new Date().getTime();
//        double dt = (end-start)/1000.0;
//        
//        assertTrue(g.nodeSize()==100,error);
//        assertTrue(dt<5,error+" time fault");
//	  
//        weighted_graph_algorithms ga = new WGraph_Algo();
//        ga.init(g);
//        assertTrue(ga.isConnected(),error+", the graph is not connected");
//
//        boolean thrown = false;
//        try {
//        	  g.addNode(300);
//        	  g.connect(0, 300, -3.1);
//              g.connect(0, 300, -3);
//        } catch (IllegalArgumentException e) {
//          thrown = true;
//        }
//        assertTrue(thrown,error+" did not throw IllegalArgumentException");
//	}	
//	
//	/**
//    *
//    *test for the function GetV
//    *in the test I build a new weighted graph and added a 5 nodes
//    *the assert true check if the function work gad give you the shallow copy of the graph 
//    */
//	@Test
//	void testGetV() {
//		weighted_graph g = new WGraph_DS();
//        for (int i = 0; i < 5; i++) {
//        	g.addNode(i);
//        }
//	for (int i = 0; i < g.nodeSize()-1; i++) {
//			g.connect(i, i+1, 0);
//		
//	}
//	  weighted_graph_algorithms ga = new WGraph_Algo();
//      ga.init(g);        
//
//      LinkedList<node_info> al = new LinkedList<>(g.getV()) ;
//      for (node_info i : al) {
//    	  i.setInfo("has been marked");
//      }
//      for (int i = 0; i < g.nodeSize()-1; i++) {
//	        assertTrue(g.getNode(i).getInfo()=="has been marked","did not change the actual graph");
//	}
//      
//      
//	}
//	/**
//    *
//    *test for the function GetVInt
//    *in the test I build a new weighted graph and added a 5 nodes
//    *the assert true check if the function returns a Collection that containing all the
//    * nodes connected to node_id 
//    */
//	@Test
//	void testGetVInt() {
//		weighted_graph g = new WGraph_DS();
//        for (int i = 0; i < 5; i++) {
//        	g.addNode(i);}
//        
//	    for (int i = 0; i < g.nodeSize(); i++) {
//			g.connect(0, i, 2);}
//		
//	    LinkedList<node_info> al = new LinkedList<>(g.getV(0)) ;
//	    assertTrue(al.size()==4,"GetVInt dosent work properly");  
//	    for (int i = 1; i < 5; i++) {
//	    	assertTrue(al.contains(g.getNode(i)),"GetVInt dosent work properly");		}
//	}
//
//	@Test
//	void testRemoveNode() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRemoveEdge() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testNodeSize() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testEdgeSize() {
//		fail("Not yet implemented");
//	}
//
//
//	@Test
//	void testToString() {
//		fail("Not yet implemented");
//	}
//
//}
