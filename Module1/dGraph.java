package FirstCode;
import java.util.*;
import java.util.Stack;

public class dGraph {
    static class Graph {
        private final Map<String, TreeSet<String>> graph;
        private final String startV;
        public Graph(String SV) {
            graph = new HashMap<>();
            startV = SV;
        }
        public void addDirectedVertexes(String first, String second) {
            if (!graph.containsKey(first)) {
                graph.put(first, new TreeSet<>());
            }
            graph.get(first).add(second);
        }
        public void addUndirectedVertexes(String first, String second) {
            addDirectedVertexes(first, second);
            addDirectedVertexes(second, first);
        }
        public String depthSearch() {
            StringBuilder result = new StringBuilder();
            Stack<String> depth = new Stack<>();
            Set<String> wasVisited = new HashSet<>();
            depth.push(startV);
            while (!depth.isEmpty()) {
                Iterator<String> iterator = graph.get(depth.peek()).descendingIterator();
                wasVisited.add(depth.peek());
                depth.pop();
                while (iterator.hasNext()) {
                    String vertex = iterator.next();
                    if (!wasVisited.contains(vertex)) depth.push(vertex);
                }
            }
            for (String s : wasVisited) result.append(s).append("\n");
            return result.toString();
        }
        public String widthSearch() {
            StringBuilder result = new StringBuilder();
            Queue<String> width = new LinkedList<>();
            Set<String> wasVisited = new HashSet<>();
            width.add(startV);
            wasVisited.add(width.peek());
            while (!width.isEmpty()) {
                if (graph.containsKey(width.peek())) {
                    for (String vertex : graph.get(width.peek())) {
                        if (!wasVisited.contains(vertex)) {
                            width.add(vertex);
                            wasVisited.add(vertex);
                        }
                    }
                }
                result.append(width.peek()).append("\n");
                width.remove();
            }
            return result.toString();
        }
    }

    public static void main(String[] args) {
        String setParameters = "^([ud])\\s\\S+\\s([bd])$";
        String vertexes = "\\S \\S";
        Scanner input = new Scanner(System.in);
        String buffer;
        Graph test = null;
        String searchType = null;
        String graphType = null;
        while (input.hasNext()) {
            buffer = input.nextLine();
            if (buffer.matches(setParameters)) {
                String[] operations = buffer.split(" ");
                test = new Graph(operations[1]);
                searchType = operations[2];
                graphType = operations[0];
                break;
            } else {
                System.out.println("error");
            }
        }
        if (graphType != null && graphType.equals("d")) {
            while (input.hasNext()) {
                buffer = input.nextLine();
                if (buffer.matches(vertexes)) {
                    String[] Vertexes = buffer.split(" ");
                    test.addDirectedVertexes(Vertexes[0], Vertexes[1]);
                } else {
                    System.out.println("error");
                }
            }
        } else {
            while (input.hasNext()) {
                buffer = input.nextLine();
                if (buffer.matches(vertexes)) {
                    String[] Vertexes = buffer.split(" ");
                    test.addUndirectedVertexes(Vertexes[0], Vertexes[1]);
                } else {
                    System.out.println("error");
                }
            }
        }
        input.close();
        if (searchType != null && searchType.equals("b")) {
            System.out.println(test.widthSearch());
        } else {
            System.out.println(Objects.requireNonNull(test).depthSearch());
        }
    }
}
