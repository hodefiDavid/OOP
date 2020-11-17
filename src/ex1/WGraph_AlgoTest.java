package ex1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest {

    @Test
    void isConnected() {
        weighted_graph g0 = WGraph_DSTest.graph_creator(0,0,1);
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertTrue(ag0.isConnected());
  
        g0 = WGraph_DSTest.graph_creator(1,0,1);
        ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertTrue(ag0.isConnected());

         g0 = WGraph_DSTest.graph_creator(2,0,1);
        ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertFalse(ag0.isConnected());
        
         g0 = WGraph_DSTest.graph_creator(2,1,1);
        ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertTrue(ag0.isConnected());

        g0 = WGraph_DSTest.graph_creator(10,30,1);
        ag0.init(g0);
        boolean b = ag0.isConnected();
        assertTrue(b);
    }

    @Test
    void shortestPathDist() {
        weighted_graph g0 = small_graph();
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        ag0.init(g0);
        assertTrue(ag0.isConnected());
        double d = ag0.shortestPathDist(0,10);
        assertEquals(d, 5.1);
    }

    @Test
    void shortestPath() {
        weighted_graph g0 = small_graph();
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        ag0.init(g0);
        List<node_info> sp = ag0.shortestPath(0,10);
        double[] checkTag = {0.0, 1.0, 2.0, 3.1, 5.1};
        int[] checkKey = {0, 1, 5, 7, 10};
        int i = 0;
        for(node_info n: sp) {
        	assertEquals(n.getTag(), checkTag[i]);
        	assertEquals(n.getKey(), checkKey[i]);
        	i++;
        }
    }
    
    @Test
    void save_load() {
        weighted_graph g0 = WGraph_DSTest.graph_creator(10,30,1);
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        ag0.init(g0);
        String str = "g0.obj";
        ag0.save(str);
        weighted_graph g1 = WGraph_DSTest.graph_creator(10,30,1);
        ag0.load(str);
        assertEquals(g0,g1);
        g0.removeNode(0);
        assertNotEquals(g0,g1);
    }

    private weighted_graph small_graph() {
        weighted_graph g0 = WGraph_DSTest.graph_creator(11,0,1);
        g0.connect(0,1,1);
        g0.connect(0,2,2);
        g0.connect(0,3,3);

        g0.connect(1,4,17);
        g0.connect(1,5,1);
        g0.connect(2,4,1);
        g0.connect(3, 5,10);
        g0.connect(3,6,100);
        g0.connect(5,7,1.1);
        g0.connect(6,7,10);
        g0.connect(7,10,2);
        g0.connect(6,8,30);
        g0.connect(8,10,10);
        g0.connect(4,10,30);
        g0.connect(3,9,10);
        g0.connect(8,10,10);

        return g0;
    }
}


//package ex1;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.text.DecimalFormat;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.LinkedHashSet;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//import org.junit.jupiter.api.Test;

//class WGraph_AlgoTest {
//	@Test
//    public void test_path() {
//        WGraph_DS g1 = new WGraph_DS();
//        weighted_graph_algorithms ga = new WGraph_Algo(g1);
//        _rnd = new Random(1);
//        int n = 20, path_size = 13;
////        int n = 2000, path_size = 500;
//
//        for (int i = 0; i < n; i++)
//            g1.addNode(i);
//        Set<Integer> s = new LinkedHashSet<>();
//        while (s.size() != path_size)
//            s.add(nextRnd(0, n-1));
//        Iterator<Integer> it = s.iterator();
//        int prev = it.next(), start = prev, next = -1;
////        System.out.print("Expected:\n(" + prev + ")");
//        List<node_info> sp_expected = new LinkedList<>();
//        sp_expected.add(g1.getNode(prev));
//        double w = 0.1, sum = 0;
//        while (it.hasNext()) {
//            next = it.next();
//            w = nextRnd(0, 1.5);
//            sum += w;
//            g1.connect(prev, next, w);
////            System.out.print(" - " + w + " -> (" + next + ") ");
//            sp_expected.add(g1.getNode(next));
//            prev = next;
//        }
//        for (int i = 1; i < n * 2; i++) {
//            int a = nextRnd(0, n-1);
//            int b = nextRnd(0, n-1);
//            if (!g1.hasEdge(a, b)) {
//                g1.connect(a, b, nextRnd(sum + 1, sum * 3));
//            }
//        }
////        System.out.println("\n=============" + ga.shortestPathDist(start, next));
////        System.out.println(sum);
//
////		        System.out.println(g1);
////        System.out.println("\nActual:");
//        List<node_info> sp_actual = ga.shortestPath(start, next);
////        for (int i = 0; i < sp_actual.size() - 1; i++) {
////            System.out.print(sp_actual.get(i) + " - " + g1.getEdge(sp_actual.get(i).getKey(), sp_actual.get(i + 1).getKey()) + " -> ");
////        }
////        System.out.print(sp_actual.get(sp_actual.size() - 1) + "\n");
////		        System.out.println(sp_expected);
////		        System.out.println(sp_actual);
//        assertEquals(sp_expected, sp_actual);
////        assertArrayEquals(sp_expected.toArray(), sp_actual.toArray());
//        assertEquals(sum, ga.shortestPathDist(start, next));
//        System.out.println();
//    }
//	@Test
//	void testInit() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetGraph() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCopy() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testIsConnected() {
//
//		weighted_graph g = new WGraph_DS();
//	
//	for (int i = 0; i < 5; i++) {
//				g.addNode(i);;
//	}
//	g.connect(0, 1, 1.2);
//	g.connect(1, 2, 10);
//	g.connect(2, 3, 5.2);
//	g.connect(3, 4, 1.7);
//		
//	weighted_graph_algorithms ga = new WGraph_Algo();
//	ga.init(g);
//	System.out.println(g);
//	System.out.println(ga.isConnected());
//
//	}
//
//	@Test
//	void testShortestPathDist() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testShortestPath() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSave() {
//
//		weighted_graph g = new WGraph_DS();
//		
//		
//	}
//
//	@Test
//	void testLoad() {
//		fail("Not yet implemented");
//	}
//
//	
//	private static Random _rnd = null;

//	@Test
//	    public void test_path() {
//	        WGraph_DS g1 = new WGraph_DS();
//	        weighted_graph_algorithms ga = new WGraph_Algo(g1);
//	        int n = 100000, path_size = 1000;
//	        for (int i = 0; i < n; i++) {
//	            g1.addNode(i);
//	        }
//	        _rnd = new Random(1);
//	        double w = 0.1, sum = 0;
//	        Set<Integer> s = new HashSet<>();
//	        while (s.size() != path_size) {
//	            s.add(nextRnd(0, n));
//	        }
//	        Iterator<Integer> it = s.iterator();
//	        int prev = it.next(), start = prev, next = -1;
////	        System.out.print("Expected:\n(" + prev + ")");
//	        List<node_info> sp_expected = new LinkedList<>();
//	        sp_expected.add(g1.getNode(prev));
//	        while (it.hasNext()) {
//	            next = it.next();
//	            w = nextRnd(0, 1.5);
//	            sum += w;
//	            g1.connect(prev, next, w);
////	            System.out.print(" - " + w + " -> (" + next + ") ");
//	            sp_expected.add(g1.getNode(next));
//	            prev = next;
//	        }
////	        System.out.println(g1);
////	        System.out.println("\nActual:");
//	        List<node_info> sp_actual = ga.shortestPath(start, next);
////	        for (int i = 0; i < sp_actual.size() - 1; i++) {
////	            System.out.print( sp_actual.get(i) + " - " + g1.getEdge(sp_actual.get(i).getKey(), sp_actual.get(i + 1).getKey()) + " -> ");
////	        }
////	        System.out.print(sp_actual.get(sp_actual.size()-1) +"\n");
//	        for (int i = 1; i < n * 3; i++) {
//	            g1.connect(nextRnd(0, n), nextRnd(0, n), i * 8);
//	        }
////	        System.out.println(g1);
//	        System.out.println(ga.shortestPathDist(start, next)
//);
//	        assertEquals(sp_expected, sp_actual);
//	        assertArrayEquals(sp_expected.toArray(), sp_actual.toArray());
//	        assertEquals(sum, ga.shortestPathDist(start, next));
//	    }


	    ////////////////////// Private Functions /////////////////////

//
//	    private static int nextRnd(int min, int max) {
//	        double v = nextRnd(0.0 + min, (double) max);
//	        int ans = (int) v;
//	        return ans;
//	    }
//
//	    private static double nextRnd(double min, double max) {
//	        double d = _rnd.nextDouble();
//	        double dx = max - min;
//	        double ans = d * dx + min;
//	        DecimalFormat df = new DecimalFormat("####0.00");
//	        ans = Double.parseDouble(df.format(ans));
//	        return ans;
//	    }
//	
//	
//	
//	
//	
//	
//}
