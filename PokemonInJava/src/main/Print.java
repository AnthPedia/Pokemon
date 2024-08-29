package main;

import item.Defensive;
import item.Offensive;
import item.Spell;
import user.User;

public class Print {
	public void clearScreen() {
		for (int i = 0; i < 64; i++) {
			System.out.println();
		}
	}
	
	public void splashScreen() {
		System.out.println(" _      __    __                     ______       ___  ____  _______      __");
		System.out.println("| | /| / /__ / /______  __ _  ___   /_  __/__    / _ \\/ __ \\/_  __/ | /| / /");
		System.out.println("| |/ |/ / -_) / __/ _ \\/  ' \\/ -_)   / / / _ \\  / // / /_/ / / /  | |/ |/ /");
		System.out.println("|__/|__/\\__/_/\\__/\\___/_/_/_/\\__/   /_/  \\___/ /____/\\____/ /_/   |__/|__/");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Enter To Continue...."); 
	}
	
	public void loginLogo() {
		System.out.println("   __             _         __  ___");
		System.out.println("  / /  ___  ___ _(_)__     /  |/  /__ ___  __ __");
		System.out.println(" / /__/ _ \\/ _ `/ / _ \\   / /|_/ / -_) _ \\/ // /");
		System.out.println("/____/\\___/\\_, /_/_//_/  /_/  /_/\\__/_//_/\\_,_/");
		System.out.println("          /___/");
		System.out.println();
	}
	
	public void registerLogo() {
		System.out.println("   ___           _     __             __  ___");
		System.out.println("  / _ \\___ ___ _(_)__ / /____ ____   /  |/  /__ ___  __ __");
		System.out.println(" / , _/ -_) _ `/ (_-</ __/ -_) __/  / /|_/ / -_) _ \\/ // /");
		System.out.println("/_/|_|\\__/\\_, /_/___/\\__/\\__/_/    /_/  /_/\\__/_//_/\\_,_/");
		System.out.println("         /___/");
		System.out.println("");
		System.out.println("");
	}
	
	public void gameLogo() {
		System.out.println(" _      __    __                      ______        ________          _____");
		System.out.println("| | /| / /__ / /______  __ _  ___    /_  __/__     /_  __/ /  ___    / ___/__ ___ _  ___");
		System.out.println("| |/ |/ / -_) / __/ _ \\/  ' \\/ -_)    / / / _ \\     / / / _ \\/ -_)  / (_ / _ `/  ' \\/ -_)");
		System.out.println("|__/|__/\\__/_/\\__/\\___/_/_/_/\\__/    /_/  \\___/    /_/ /_//_/\\__/   \\___/\\_,_/_/_/_/\\__/");
		System.out.println("");
	}
	
	public void guide() {
		System.out.println("  _____                  _____     _    __");
		System.out.println(" / ___/__ ___ _  ___    / ___/_ __(_)__/ /__");
		System.out.println("/ (_ / _ `/  ' \\/ -_)  / (_ / // / / _  / -_)");
		System.out.println("\\___/\\_,_/_/_/_/\\__/   \\___/\\_,_/_/\\_,_/\\__/");
		System.out.println("");
		System.out.println("Hello this is the case maker writing, this game is inspired by my favorite game of all time that is DOTA the game is" + "\n" + "really simple where you move inside the game using general controls, In the game you can collect coins as you move." + "\n" + "You can also meet enemies while going through the grass in the game. If you have killed an enemy you will be rewarded " + "\n" + "money that you can use again to buy the item");
		System.out.println("");
		System.out.println("Character Informations");
		System.out.println("");
		System.out.println("  Coin : O");
		System.out.println("  Grass : v | V");
		System.out.println("  Character : X");
		System.out.println("  Wall : #");
		System.out.println("");
		System.out.println("");
		System.out.println("Keybind Information");
		System.out.println("");
		System.out.println("  w          : Move character up");
		System.out.println("  a          : Move character left");
		System.out.println("  s          : Move character down");
		System.out.println("  d          : Move character right");
		System.out.println("  i          : Show all character's item");
		System.out.println("  z          : Shop Menu");
		System.out.println("  e          : Exit game and save your information");
		System.out.println("");
		System.out.println("Enter To Continue....");
	}
	
	public void exit() {
		System.out.println("   ____      _ __");
		System.out.println("  / __/_ __ (_) /_");
		System.out.println(" / _/ \\ \\ // / __/");
		System.out.println("/___//_\\_\\/_/\\__/");
		System.out.println("");
		System.out.println("Saving your progress....");
		System.out.println("Enter To Continue....");
		System.out.println("");
	}
	
	public void printDisplay(char[][] map, User user, int playerY, int playerX, int cameraY, int cameraX) {
	    int mapHeight = map.length;
	    int mapWidth = map[0].length;

	    for (int i = 0; i < 37; i++) {
	        System.out.print("-");
	    }
	    System.out.println();

	    for (int i = playerY - cameraY; i <= playerY + cameraY; i++) {
	        System.out.print("|");
	        for (int j = playerX - cameraX; j <= playerX + cameraX; j++) {
	            if (i >= 0 && i < mapHeight && j >= 0 && j < mapWidth) {
	                if (i == playerY && j == playerX) {
	                    System.out.print("X");
	                } else {
	                    System.out.print(map[i][j]);
	                }
	            } else {
	                System.out.print(" ");
	            }
	        }

	        System.out.println("|");
	    }

	    for (int i = 0; i < 37; i++) {
	        System.out.print("-");
	    }
	    System.out.println();

	    System.out.println();
	    System.out.println("Player Information " + user.getEmail());
	    System.out.println("===================================");
	    System.out.printf("|Health          : %-15.1f|\n", user.getHealth());
	    System.out.printf("|Money           : %-15d|\n", user.getMoney());
	    System.out.printf("|Mana            : %-15.2f|\n", user.getMana());
	    System.out.printf("|Base Damage     : %-15.2f|\n", user.getAttack());
	    System.out.println("===================================");
	}
	
	public void printShop() {
		System.out.println("   ______               __  ___");
		System.out.println("  / __/ /  ___  ___    /  |/  /__ ___  __ __");
		System.out.println(" _\\ \\/ _ \\/ _ \\/ _ \\  / /|_/ / -_) _ \\/ // /");
		System.out.println("/___/_//_/\\___/ .__/ /_/  /_/\\__/_//_/\\_,_/");
		System.out.println("             /_/");
		System.out.println("");
	}
	
	public void offensive() {
		System.out.println("  ____  ______             _");
		System.out.println(" / __ \\/ _/ _/__ ___  ___ (_)  _____");
		System.out.println("/ /_/ / _/ _/ -_) _ \\(_-</ / |/ / -_)");
		System.out.println("\\____/_//_/ \\__/_//_/___/_/|___/\\__/");
		System.out.println("");
		System.out.println("");
	}
	
	public void offensiveBar() {
		System.out.println("============================================================================================");
		System.out.println("|ID        |Name                          |Type           |Price     |Damage    |Max Use   |");
		System.out.println("============================================================================================");
	}
	
	public void defensive() {
		System.out.println("   ___      ___             _");
		System.out.println("  / _ \\___ / _/__ ___  ___ (_)  _____");
		System.out.println(" / // / -_) _/ -_) _ \\(_-</ / |/ / -_)");
		System.out.println("/____/\\__/_/ \\__/_//_/___/_/|___/\\__/");
		System.out.println("");
		System.out.println("");
	}
	
	public void defensiveBar() {
		System.out.println("============================================================================================");
		System.out.println("|ID        |Name                          |Type           |Price     |Deflect   |Max Use   |");
		System.out.println("============================================================================================");
	}
	
	public void spell() {
		System.out.println("   ____         ____");
		System.out.println("  / __/__  ___ / / /");
		System.out.println(" _\\ \\/ _ \\/ -_) / /");
		System.out.println("/___/ .__/\\__/_/_/");
		System.out.println("   /_/");
		System.out.println("");
		System.out.println("");
	}
	
	public void spellBar() {
		System.out.println("============================================================================================");
		System.out.println("|ID        |Name                          |Type           |Price     |Damage    |Mana      |");
		System.out.println("============================================================================================");
	}
	
	public void fight() {
		System.out.println("   _____      __   __");
		System.out.println("  / __(_)__ _/ /  / /_");
		System.out.println(" / _// / _ `/ _ \\/ __/");
		System.out.println("/_/ /_/\\_, /_//_/\\__/");
		System.out.println("      /___/");
		System.out.println("");
		System.out.println("");
	}
	
	public void playerTurn() {
		System.out.println("   ___  __                       ______");
		System.out.println("  / _ \\/ /__ ___ _____ ____     /_  __/_ _________");
		System.out.println(" / ___/ / _ `/ // / -_) __/      / / / // / __/ _ \\");
		System.out.println("/_/  /_/\\_,_/\\_, /\\__/_/        /_/  \\_,_/_/ /_//_/");
		System.out.println("            /___/");
		System.out.println("");
		System.out.println("");
	}
	
	public void monsterTurn() {
		System.out.println("   __  ___              __              ______");
		System.out.println("  /  |/  /__  ___  ___ / /____ ____    /_  __/_ _________");
		System.out.println(" / /|_/ / _ \\/ _ \\(_-</ __/ -_) __/     / / / // / __/ _ \\");
		System.out.println("/_/  /_/\\___/_//_/___/\\__/\\__/_/       /_/  \\_,_/_/ /_//_/");
		System.out.println("");
		System.out.println("");
	}
	
	public void information() {
		System.out.println("  _____                      __      ____     ___                    __  _");
		System.out.println(" / ___/_ _____________ ___  / /_    /  _/__  / _/__  ______ _  ___ _/ /_(_)__  ___");
		System.out.println("/ /__/ // / __/ __/ -_) _ \\/ __/   _/ // _ \\/ _/ _ \\/ __/  ' \\/ _ `/ __/ / _ \\/ _ \\");
		System.out.println("\\___/\\_,_/_/ /_/  \\__/_//_/\\__/   /___/_//_/_/ \\___/_/ /_/_/_/\\_,_/\\__/_/\\___/_//_/");
		System.out.println("");
		System.out.println("");
	}
	
	public void itemsList() {
		System.out.println("   ______");
		System.out.println("  /  _/ /____ __ _  ___");
		System.out.println(" _/ // __/ -_)  ' \\(_-<");
		System.out.println("/___/\\__/\\__/_/_/_/___/");
		System.out.println("");
		System.out.println("");
	}
	
	public void ownedItem(User cur) {
		System.out.println("=========================================================================================================");
		System.out.println("|ID        |Name                          |Type           |Price     |Damage    |Max Use/Mana|Use Left  |");
		System.out.println("=========================================================================================================");
		if (cur.getItems() != null) {
			for (int i = 0; i < cur.getItems().size(); i++) {
				if (cur.getItems().get(i) instanceof Defensive) {
					Defensive def = (Defensive) cur.getItems().get(i);
					System.out.printf("|%-10s|%-30s|%-15s|%-10d|%-10.2f|%-12d|%-10d|\n", def.getId(), def.getName(), "Defensive", def.getPrice(), def.getDeflect(), def.getMaxUse(), def.getMaxUse() - def.getTimeUsed());
				} else if (cur.getItems().get(i) instanceof Offensive) {
					Offensive off = (Offensive) cur.getItems().get(i);
					System.out.printf("|%-10s|%-30s|%-15s|%-10d|%-10.2f|%-12d|%-10d|\n", off.getId(), off.getName(), "Offensive", off.getPrice(), off.getDamage(), off.getMaxUse(), off.getMaxUse() - off.getTimeUsed());
				} else if (cur.getItems().get(i) instanceof Spell) {
					Spell sp = (Spell) cur.getItems().get(i);
					System.out.printf("|%-10s|%-30s|%-15s|%-10d|%-10.2f|%-12.2f|%-10s|\n", sp.getId(), sp.getName(), "Spells", sp.getPrice(), sp.getDamage(), sp.getMana(), "-");
				}
			}
		}
		System.out.println("=========================================================================================================");
		System.out.println();
	}
	
	public void attacking() {
		System.out.println("   ___  __  __           __    _");
		System.out.println("  / _ |/ /_/ /____ _____/ /__ (_)__  ___ _");
		System.out.println(" / __ / __/ __/ _ `/ __/  '_// / _ \\/ _ `/");
		System.out.println("/_/ |_\\__/\\__/\\_,_/\\__/_/\\_\\/_/_//_/\\_, /");
		System.out.println("                                   /___/");
		System.out.println("");
		System.out.println("");
	}
	
	public void win() {
		System.out.println("   __  ___              __             ___  _        __");
		System.out.println("  /  |/  /__  ___  ___ / /____ ____   / _ \\(_)__ ___/ /");
		System.out.println(" / /|_/ / _ \\/ _ \\(_-</ __/ -_) __/  / // / / -_) _  /");
		System.out.println("/_/  /_/\\___/_//_/___/\\__/\\__/_/    /____/_/\\__/\\_,_/");
		System.out.println("");
		System.out.println("");
	}
	
	public void lose() {
		System.out.println("   ___  _        __");
		System.out.println("  / _ \\(_)__ ___/ /");
		System.out.println(" / // / / -_) _  /");
		System.out.println("/____/_/\\__/\\_,_/");
		System.out.println("");
		System.out.println("");
	}
	
}
