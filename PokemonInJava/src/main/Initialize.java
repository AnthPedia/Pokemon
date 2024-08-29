package main;

import java.util.Random;

public class Initialize {
	
	Random rand = new Random();
	
	void fillMap(char[][] map, char[][] target, int height, int weight) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				map[height + i][weight + j] = target[i][j];
			}
		}
	}
	
	boolean checkMap(char[][] map, int height, int weight) {
		for (int i = Math.max(0, height - 6); i < Math.min(map.length, height + 6); i++) {
			for (int j = Math.max(0, weight - 6); j < Math.min(map[0].length, weight + 6); j++) {
				if(map[i][j] == '1' || map[i][j] == '2') {
					return false;
				}
			}
		}
		return true;
	}
	
	public void initFirst(char[][] map, int height, int weight) {
		char[][] empty = {
				{'0', '0', '0'},
				{'0', '0', '0'},
				{'0', '0', '0'}
		};
		char[][] grass = {
				{'1', '1', '1'},
				{'1', '1', '1'},
				{'1', '1', '1'}
		};
		char[][] wall = {
				{'2', '2', '2'},
				{'2', '2', '2'},
				{'2', '2', '2'}
		};
		
		for (int i = 0; i < height; i += 3) {
			for (int j = 0; j < weight; j += 3) {
				int type = rand.nextInt(3);
				char[][] temp;
				if (!checkMap(map, i, j)) {
					temp = empty;
				} else {
					if (type == 0) {
						temp = empty;
					} else if (type == 1) {
						temp = grass;
					} else {
						temp = wall;
					}
				}
				fillMap(map, temp, i, j);
			}
		}
	}
	
	public void initMap(char[][] map, int height, int weight) {
		initFirst(map, height, weight);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < weight; j++) {
				if (map[i][j] == '0') {
					int coin = rand.nextInt(300);
					if (coin == 1) {
						map[i][j] = 'O';
					} else {
						map[i][j] = ' ';
					}
				} else if (map[i][j] == '1') {
					char[] grass = {' ', 'v', 'V'};
					map[i][j] = grass[rand.nextInt(3)];
				} else if (map[i][j] == '2') {
					char[] wall = {' ', '#'};
					map[i][j] = wall[rand.nextInt(2)];
				}
			}
		}
	}
}
