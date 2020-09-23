import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


public class RouteCalculatorTest extends TestCase {
    RouteCalculator routeCalculator;
    List<Station> route1;
    List<Station> route2;
    List<Station> route3;
    List<Station> connection;
    List<Station> connection1;
    StationIndex stationIndex;


    @Override
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();

        Line line1 = new Line(1, "Первая Горизонтальная");
        Line line2 = new Line(2, "Вертикальная");
        Line line3 = new Line(3, "Вторая Горизонтальная");

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);


        List<Station> stations = new ArrayList<Station>() {{
            add(new Station("Горизонт1", stationIndex.getLine(1)));
            add(new Station("Горизонт2", stationIndex.getLine(1)));
            add(new Station("Горизонт3", stationIndex.getLine(1)));
            add(new Station("Горизонт4", stationIndex.getLine(1)));
            //  add(new Station("Горизонт5",stationIndex.getLine(1)));

            add(new Station("Вертикал1", stationIndex.getLine(2)));
            add(new Station("Вертикал2", stationIndex.getLine(2)));
            add(new Station("Вертикал3", stationIndex.getLine(2)));
            add(new Station("Вертикал4", stationIndex.getLine(2)));

            add(new Station("ВторГор1", stationIndex.getLine(3)));
            add(new Station("ВторГор2", stationIndex.getLine(3)));
            add(new Station("ВторГор3", stationIndex.getLine(3)));
            add(new Station("ВторГор4", stationIndex.getLine(3)));
            //  add(new Station("ВторГор5",stationIndex.getLine(3)));
        }};


        for (int i = 0; i < 4; i++) {
            stationIndex.getLine(1).addStation(stations.get(i));
        }

        for (int i = 4; i < 8; i++) {
            stationIndex.getLine(2).addStation(stations.get(i));
        }

        for (int i = 8; i < 12; i++) {
            stationIndex.getLine(3).addStation(stations.get(i));
        }

        for (Station station : stations) {
            stationIndex.addStation(station);
        }

        connection = new ArrayList<Station>() {{
            add(stationIndex.getStation("Горизонт3", 1));
            add(stationIndex.getStation("Вертикал1", 2));
        }};

        connection1 = new ArrayList<Station>() {{
            add(stationIndex.getStation("Вертикал4", 2));
            add(stationIndex.getStation("ВторГор3", 3));
        }};

        stationIndex.addConnection(connection);
        stationIndex.addConnection(connection1);

        route1 = new ArrayList<>();
        route1.add(stationIndex.getStation("Горизонт1"));
        route1.add(stationIndex.getStation("Горизонт2"));
        route1.add(stationIndex.getStation("Горизонт3"));
        route1.add(stationIndex.getStation("Горизонт4"));

        route2 = new ArrayList<>();
        route2.add(stationIndex.getStation("Горизонт1"));
        route2.add(stationIndex.getStation("Горизонт2"));
        route2.add(stationIndex.getStation("Горизонт3"));
        route2.add(stationIndex.getStation("Вертикал1"));
        route2.add(stationIndex.getStation("Вертикал2"));

        route3 = new ArrayList<>();
        route3.add(stationIndex.getStation("Горизонт1"));
        route3.add(stationIndex.getStation("Горизонт2"));
        route3.add(stationIndex.getStation("Горизонт3"));
        route3.add(stationIndex.getStation("Вертикал1"));
        route3.add(stationIndex.getStation("Вертикал2"));
        route3.add(stationIndex.getStation("Вертикал3"));
        route3.add(stationIndex.getStation("Вертикал4"));
        route3.add(stationIndex.getStation("ВторГор3"));
        route3.add(stationIndex.getStation("ВторГор4"));


        routeCalculator = new RouteCalculator(stationIndex);
    }

    public void testGetRouteOnTheLine() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Method method = RouteCalculator.class.getDeclaredMethod("getRouteOnTheLine", Station.class, Station.class);
        method.setAccessible(true);
        List<Station> output = (List<Station>) method.invoke(routeCalculator, route1.get(0), route2.get(route2.size() - 1));
        assertNull(output);
    }

    public void testGetRouteWithOneConnection() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Method method = RouteCalculator.class.getDeclaredMethod("getRouteWithOneConnection", Station.class, Station.class);
        method.setAccessible(true);
        List<Station> output = (List<Station>) method.invoke(routeCalculator, route1.get(0), route1.get(1));
        assertNull(output);
        List<Station> outlaw = (List<Station>) method.invoke(routeCalculator, route1.get(route1.size() - 1), route3.get(route3.size() - 1));
        assertNull(outlaw); // попытался проверить выведет ли null метод, если начальная и конечная станции
        // находятся на концах линий

    }

    public void testGetRouteViaConnectedLine() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Method method = RouteCalculator.class.getDeclaredMethod("getRouteViaConnectedLine", Station.class, Station.class);
        method.setAccessible(true);
        List<Station> output = (List<Station>) method.invoke(routeCalculator, route1.get(0), route1.get(1));
        assertNull(output);
    }

    public void testGetRouteWithTwoConnections() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Method method = RouteCalculator.class.getDeclaredMethod("getRouteWithTwoConnections", Station.class, Station.class);
        method.setAccessible(true);
        List<Station> output = (List<Station>) method.invoke(routeCalculator, route1.get(0), route1.get(1));
        assertNull(output);
    }

    public void testGetShortestRoute() {
        List<Station> actual1 = routeCalculator.getShortestRoute(route1.get(0), route1.get(route1.size() - 1));
        List<Station> expected1 = route1;
        assertEquals(actual1, expected1);
        List<Station> actual2 = routeCalculator.getShortestRoute(route2.get(0), route2.get(route2.size() - 1));
        List<Station> expected2 = route2;
        assertEquals(actual2, expected2);
        List<Station> actual3 = routeCalculator.getShortestRoute(route3.get(0), route3.get(route3.size() - 1));
        List<Station> expected3 = route3;
        assertEquals(actual3, expected3);

    }

    public void testCalculateDuration() {
        double actual1 = RouteCalculator.calculateDuration(route1);
        double expected1 = 7.5;
        assertEquals(actual1, expected1);
        double actual2 = RouteCalculator.calculateDuration(route2);
        double expected2 = 11.0;
        assertEquals(actual2, expected2);
        double actual3 = RouteCalculator.calculateDuration(route3);
        double expected3 = 22.0;
        assertEquals(actual3, expected3);
        double actual4 = RouteCalculator.calculateDuration(Collections.singletonList(route3.get(0)));
        double expected4 = 0.0;
        assertEquals(actual4, expected4);
    }

    @Override
    protected void tearDown() throws Exception // удаляет данные после теста
    {

    }
}
