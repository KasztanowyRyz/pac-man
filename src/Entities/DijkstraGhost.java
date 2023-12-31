package src.Entities;

import src.Board;
import src.Entities.MovingEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @brief DijkstraGhost class represents a ghost character that uses Dijkstra's algorithm to calculate the shortest path to the player.
 *
 * This class extends the MovingEntity class and implements the movement behavior specific to a DijkstraGhost.
 */
public class DijkstraGhost extends MovingEntity {
    private List<Point> shortestPath;
    private Queue<Point> pathQueue;

    /**
     * @brief Constructs a DijkstraGhost object with the specified image path, speed, and starting position.
     *
     * @param imagePath The path to the image representing the ghost.
     * @param speed The speed of the ghost.
     * @param startPos The starting position of the ghost.
     */
    public DijkstraGhost(String imagePath, float speed, Point startPos) {
        super(speed, startPos);
        setSprite(imagePath);
        shortestPath = new ArrayList<>();
        pathQueue = new LinkedList<>();
    }

    /**
     * @brief Moves the ghost according to Dijkstra's algorithm.
     *
     * This method calculates the shortest path to the player using Dijkstra's algorithm.
     * It updates the ghost's position based on the calculated path and handles collisions with walls.
     */
    @Override
    protected void Move() {
        if (!pathQueue.isEmpty()) {
            Point nextPos = pathQueue.peek();
            direction.x = nextPos.x - pos.x;
            direction.y = nextPos.y - pos.y;

            // Check for collision before updating position
            if (!checkWallCollision(direction)) {
                // Update position if there is no collision

                pos.setLocation(nextPos);

            }

            if (pos.equals(nextPos)) {
                pathQueue.poll(); // Remove the current position from the queue
            }
        }

        calculateShortestPath();
        if (shortestPath.isEmpty()) {
            return; // No valid path found
        }

        pathQueue.clear();
        pathQueue.addAll(shortestPath);
        pathQueue.poll(); // Skip the first position (current position)
    }


    /**
     * @brief Calculates the shortest path to the player using Dijkstra's algorithm.
     *
     * This method calculates the shortest path to the player using Dijkstra's algorithm and updates the shortestPath list accordingly.
     */
    private void calculateShortestPath() {
        int[][] distance = new int[Board.ROWS][Board.COLUMNS];
        Point[][] previous = new Point[Board.ROWS][Board.COLUMNS];

        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLUMNS; j++) {
                distance[i][j] = Integer.MAX_VALUE;
                previous[i][j] = null;
            }
        }

        distance[pos.y][pos.x] = 0;

        Queue<Point> queue = new LinkedList<>();
        queue.offer(pos);

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int dx = 0, dy = 0;
                if (i == 0) dy = -1; // Up
                else if (i == 1) dx = 1; // Right
                else if (i == 2) dy = 1; // Down
                else if (i == 3) dx = -1; // Left

                int newX = current.x + dx;
                int newY = current.y + dy;

                if (newX >= 0 && newX < Board.COLUMNS && newY >= 0 && newY < Board.ROWS &&
                        Board.MAP[newY][newX] == 0 &&
                        distance[newY][newX] == Integer.MAX_VALUE) {
                    distance[newY][newX] = distance[current.y][current.x] + 1;
                    previous[newY][newX] = current;
                    queue.offer(new Point(newX, newY));
                }
            }
        }

        shortestPath.clear();
        Point target = Board.getPlayer().getPos();
        Point current = target;

        while (previous[current.y][current.x] != null) {
            shortestPath.add(0, current);
            current = previous[current.y][current.x];
        }

        if (!shortestPath.isEmpty()) {
            shortestPath.add(0, current); // Add the start position to the path
        }
    }
}

