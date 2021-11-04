package me.andre.mcplugz;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Shapes {

	public static List<Location> sphere(Location centerBlock, int radius, boolean fill){
		List<Location> sphere = new ArrayList<>();

		int bX = centerBlock.getBlockX();
		int bY = centerBlock.getBlockY();
		int bZ = centerBlock.getBlockZ();

		for(int x = bX - radius; x <= bX + radius; x++){
			for(int y = bY - radius; y <= bY + radius; y++){
				for(int z = bZ - radius; z <= bZ + radius; z++){
					double distance = ((bX - x) * (bX - x) + ((bZ - z) * (bZ - z)) + ((bY - y) * (bY - y)));

					if((distance < radius * radius) && fill && distance < ((radius - 1) * (radius - 1))){
						Location loc = new Location(centerBlock.getWorld(), x, y, z);
						sphere.add(loc);
					}
				}
			}
		}

		return sphere;
	}

	public static List<Location> cube(Location centerBlock, int radius, boolean fill, boolean fromTop){
		List<Location> cube = new ArrayList<>();

		if(fill){
			if(fromTop){
				for(int x = centerBlock.getBlockX() - radius; x <= centerBlock.getBlockX() + radius; x++){
					for(int y = centerBlock.getBlockY() + radius; y >= centerBlock.getBlockY() - radius; y--){
						for(int z = centerBlock.getBlockZ() - radius; z <= centerBlock.getBlockZ() + radius; z++){
							cube.add(new Location(centerBlock.getWorld(), x, y, z));
						}
					}
				}
			}else {
				for(int x = centerBlock.getBlockX() - radius; x <= centerBlock.getBlockX() + radius; x++){
					for(int y = centerBlock.getBlockY() - radius; y <= centerBlock.getBlockY() + radius; y++){
						for(int z = centerBlock.getBlockZ() - radius; z <= centerBlock.getBlockZ() + radius; z++){
							cube.add(new Location(centerBlock.getWorld(), x, y, z));
						}
					}
				}
			}
		}
		else{

			if(fromTop){
				// Wall Yp
				for(int z = centerBlock.getBlockZ() - radius; z <= centerBlock.getBlockZ() + radius; z++){
					for(int x = centerBlock.getBlockX() - radius; x <= centerBlock.getBlockX() + radius; x++){
						cube.add(new Location(centerBlock.getWorld(), x, centerBlock.getBlockY() + radius, z));
					}
				}
			}else {
				// Wall Yn
				for(int z = centerBlock.getBlockZ() - radius; z <= centerBlock.getBlockZ() + radius; z++){
					for(int x = centerBlock.getBlockX() - radius; x <= centerBlock.getBlockX() + radius; x++){
						cube.add(new Location(centerBlock.getWorld(), x, centerBlock.getBlockY() - radius, z));
					}
				}
			}

			// Wall Xp
			for(int z = centerBlock.getBlockZ() - radius; z <= centerBlock.getBlockZ() + radius; z++){
				for(int y = centerBlock.getBlockY() - radius; y <= centerBlock.getBlockY() + radius; y++){
					cube.add(new Location(centerBlock.getWorld(), centerBlock.getBlockX() + radius, y, z));
				}
			}

			// Wall Xn
			for(int z = centerBlock.getBlockZ() - radius; z <= centerBlock.getBlockZ() + radius; z++){
				for(int y = centerBlock.getBlockY() - radius; y <= centerBlock.getBlockY() + radius; y++){
					cube.add(new Location(centerBlock.getWorld(), centerBlock.getBlockX() - radius, y, z));
				}
			}

			// Wall Zp
			for(int x = centerBlock.getBlockX() - radius; x <= centerBlock.getBlockX() + radius; x++){
				for(int y = centerBlock.getBlockY() - radius; y <= centerBlock.getBlockY() + radius; y++){
					cube.add(new Location(centerBlock.getWorld(), x + radius, y, centerBlock.getBlockZ() + radius));
				}
			}

			// Wall Zn
			for(int x = centerBlock.getBlockX() - radius; x <= centerBlock.getBlockX() + radius; x++){
				for(int y = centerBlock.getBlockY() - radius; y <= centerBlock.getBlockY() + radius; y++){
					cube.add(new Location(centerBlock.getWorld(), x + radius, y, centerBlock.getBlockZ() - radius));
				}
			}

			if(!fromTop){
				// Wall Yp
				for(int z = centerBlock.getBlockZ() - radius; z <= centerBlock.getBlockZ() + radius; z++){
					for(int x = centerBlock.getBlockX() - radius; x <= centerBlock.getBlockX() + radius; x++){
						cube.add(new Location(centerBlock.getWorld(), x, centerBlock.getBlockY() + radius, z));
					}
				}
			}else {
				// Wall Yn
				for(int z = centerBlock.getBlockZ() - radius; z <= centerBlock.getBlockZ() + radius; z++){
					for(int x = centerBlock.getBlockX() - radius; x <= centerBlock.getBlockX() + radius; x++){
						cube.add(new Location(centerBlock.getWorld(), x, centerBlock.getBlockY() - radius, z));
					}
				}
			}
		}

		return cube;
	}
}
