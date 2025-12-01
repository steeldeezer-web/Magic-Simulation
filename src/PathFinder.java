import java.util.*;

public class PathFinder {
    //используется BFS для последовательного обхода.
    public static <T extends Entity> List<Coordinate> findPathToNearestEntity(Coordinate start, WorldMap map, Class<T> targetClass ){
        //Очередь - первый вошел первый ушел
        Queue<Coordinate> queue = new LinkedList<>();
        //Коллекция для  хранения родителя каждой координаты, для восстановления пути
        Map<Coordinate,Coordinate> parrents = new HashMap<>();
        //Множетсво для хранения посещенных координат
        Set<Coordinate> visited = new HashSet<>();

        //Добавляем начальную координату в очередь
        queue.add(start);
        //Добавляем коринату в посещенные чтобы повторно не проходиться по ней
        visited.add(start);
        //У начальной координаты нет родителя
        parrents.put(start, null);

        //задает сдвиги для соседних координат в четырех направлениях
        int[][] directions = {{1, 0},{-1, 0}, {0, 1}, {0, -1}};

        //Цикл действует пока в очереди есть координаты
        while (!queue.isEmpty()){
            //Получаем координату из очереди
            Coordinate current = queue.poll();
            // Получаем сущность по этой координате
            Entity entity = map.getEntityAt(current);
            // Если объект entity не равен null,
            // и объект принадлежит (является экземпляром) класса targetClass или его подкласса,
            // и текущая координата current не совпадает с начальной координатой start,
            // то возвращаем путь, построенный из родителей
            if(entity != null && targetClass.isInstance(entity) && !current.equals(start)){
                return buildPath(parrents, current);
            }
            for(int[] dir : directions){
                //Получаем следующую координату
                Coordinate next = new Coordinate(entity.getCoordinate().getX() + dir[0], entity.getCoordinate().getY() + dir[1]);
                //Если координата находится в пределах карты и не находится в списке посещенных и (является свободной или является объектом класса  targetClass или его подкласс
                if(map.isWithInBounds(next) && !visited.contains(next) && (map.isSpotFree(next) || targetClass.isInstance(map.getEntityAt(next)))){
                    //Добавляем в очередь для посещения
                    queue.add(next);
                    //Добавляем в список посещенных чтобы избежать повторного добавления в очередь
                    visited.add(next);
                    //Добавляем координату родителя
                    parrents.put(next, current);
                }
            }
        }
        return null;
    }
    //Метод возвращает параметризированный список координат - путь до объекта класса targetClass или его подкласса,
    private static List<Coordinate> buildPath(Map<Coordinate,Coordinate> parrents, Coordinate end){
        //по сути преобразуем Коллекцию в последовательный список
        List<Coordinate> path = new ArrayList<>();
        //Переменная at получается значения финальной координаты, добавляем ее в список, далее at получается значение своего родителя
        for (Coordinate at = end ; at != null; at = parrents.get(at)){
            path.add(at);

        }
        Collections.reverse(path);
        return path;
    }
}
