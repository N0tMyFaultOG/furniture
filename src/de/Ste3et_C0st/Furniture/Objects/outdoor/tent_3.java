package de.Ste3et_C0st.Furniture.Objects.outdoor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.EulerAngle;

import de.Ste3et_C0st.Furniture.Main.main;
import de.Ste3et_C0st.FurnitureLib.Events.FurnitureBreakEvent;
import de.Ste3et_C0st.FurnitureLib.Events.FurnitureClickEvent;
import de.Ste3et_C0st.FurnitureLib.Utilitis.LocationUtil;
import de.Ste3et_C0st.FurnitureLib.main.ArmorStandPacket;
import de.Ste3et_C0st.FurnitureLib.main.Furniture;
import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.FurnitureManager;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
import de.Ste3et_C0st.FurnitureLib.main.Type.BodyPart;
import de.Ste3et_C0st.FurnitureLib.main.Type.ColorType;

public class tent_3 extends Furniture implements Listener{

	Location loc;
	BlockFace b;
	World w;
	ObjectID obj;
	FurnitureManager manager;
	FurnitureLib lib;
	LocationUtil lutil;
	Integer id;
	Plugin plugin;
	
	public ObjectID getObjectID(){return this.obj;}
	public Location getLocation(){return this.loc;}
	public BlockFace getBlockFace(){return this.b;}
	
	public tent_3(FurnitureLib lib, Plugin plugin, ObjectID id){
		super(lib, plugin, id);
		this.lutil = main.getLocationUtil();
		this.b = lutil.yawToFace(id.getStartLocation().getYaw());
		this.loc = id.getStartLocation().getBlock().getLocation();
		this.loc.setYaw(id.getStartLocation().getYaw());
		this.w = id.getStartLocation().getWorld();
		this.manager = lib.getFurnitureManager();
		this.lib = lib;
		this.plugin = plugin;
		this.obj = id;
		setBlock();
		if(id.isFinish()){
			Bukkit.getPluginManager().registerEvents(this, plugin);
			return;
		}
		if(b.equals(BlockFace.WEST)){loc=lutil.getRelativ(loc, b, 1D, 0D);}
		if(b.equals(BlockFace.NORTH)){loc=lutil.getRelativ(loc, b, 1D, 1D);}
		if(b.equals(BlockFace.EAST)){loc=lutil.getRelativ(loc, b, 0D, 1D);}
		
		spawn(loc);
	}
	
	Block bed;
	
	public void spawn(Location loc){
		List<ArmorStandPacket> aspL = new ArrayList<ArmorStandPacket>();
		ItemStack banner = new ItemStack(Material.BANNER);
		BannerMeta meta = (BannerMeta) banner.getItemMeta();
		meta.setBaseColor(DyeColor.WHITE);
		banner.setItemMeta(meta);
		
		Location locstart = lutil.getRelativ(loc, b, .2D, -.17D);
		locstart.add(0,.65,0);
		
		Location locstart2 = lutil.getRelativ(loc, b, .2D, -.8D);
		locstart2.add(0,.65,0);
		
		for(int i = 0; i<=2;i++){
			Location location = lutil.getRelativ(locstart, b, .74*i, 0D);
			location.setYaw(lutil.FaceToYaw(b)+90);
			ArmorStandPacket as = manager.createArmorStand(obj, location);
			as.getInventory().setHelmet(banner);
			as.setPose(new EulerAngle(3.5, 0, 0), BodyPart.HEAD);
			aspL.add(as);

			location = lutil.getRelativ(locstart, b, .74*i, -.3D);
			location.add(0,.9,0);
			location.setYaw(lutil.FaceToYaw(b)+90);
			
			as = manager.createArmorStand(obj, location);
			as.getInventory().setHelmet(banner);
			as.setPose(new EulerAngle(3.5, 0, 0), BodyPart.HEAD);
			aspL.add(as);
		}
		
		for(int i = 0; i<=2;i++){
			Location location = lutil.getRelativ(locstart2, b, .74*i, 0D);
			location.setYaw(lutil.FaceToYaw(b)-90);
			
			ArmorStandPacket as = manager.createArmorStand(obj, location);
			as.getInventory().setHelmet(banner);
			as.setPose(new EulerAngle(3.5, 0, 0), BodyPart.HEAD);
			aspL.add(as);
			
			location = lutil.getRelativ(locstart2, b, .74*i, .32D);
			location.add(0,.9,0);
			location.setYaw(lutil.FaceToYaw(b)-90);
			as = manager.createArmorStand(obj, location);
			as.getInventory().setHelmet(banner);
			as.setPose(new EulerAngle(3.5, 0, 0), BodyPart.HEAD);
			aspL.add(as);
		}
		
		banner = new ItemStack(Material.BANNER);
		meta = (BannerMeta) banner.getItemMeta();
		meta.setBaseColor(DyeColor.WHITE);
		meta.addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_SMALL));
		banner.setItemMeta(meta);
		
		Location banner1 = lutil.getRelativ(loc, b, 1.7D, -.1D);
		banner1.add(0,-1.2,0);
		Location banner2 = lutil.getRelativ(banner1, b, 0D, -.75);
		banner1.setYaw(lutil.FaceToYaw(b));
		banner2.setYaw(lutil.FaceToYaw(b));
		
		ArmorStandPacket as = manager.createArmorStand(obj, banner1);
		as.getInventory().setHelmet(banner);
		as.setPose(new EulerAngle(-1.568, 0, 0), BodyPart.HEAD);
		aspL.add(as);
		
		as = manager.createArmorStand(obj, banner2);
		as.getInventory().setHelmet(banner);
		as.setPose(new EulerAngle(-1.568, 0, 0), BodyPart.HEAD);
		aspL.add(as);
		
		Location sit = lutil.getCenter(loc);
		
		if(b.equals(BlockFace.WEST)){sit=lutil.getRelativ(sit, b, -1D, 0D);}
		if(b.equals(BlockFace.NORTH)){sit=lutil.getRelativ(sit, b, -1D, -1D);}
		if(b.equals(BlockFace.EAST)){sit=lutil.getRelativ(sit, b, 0D, -1D);}
		
		sit.setYaw(lutil.FaceToYaw(this.b.getOppositeFace()));
		Location locationsit = lutil.getRelativ(sit, b, 1D, 0D);
		locationsit.setYaw(lutil.FaceToYaw(this.b.getOppositeFace()));
		
		as = manager.createArmorStand(obj, locationsit.add(0,-2,0));
		as.setName("#SITZ#");
		aspL.add(as);
		
		for(ArmorStandPacket packet : aspL){
			packet.setInvisible(true);
			packet.setGravity(false);
		}
		manager.send(obj);
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	private void setBlock(){
		Location sit = lutil.getCenter(loc);
		sit.setYaw(lutil.FaceToYaw(this.b.getOppositeFace()));
		bed = lutil.setHalfBed(b, lutil.getRelativ(sit.add(0,-2,0).getBlock().getLocation().add(0,2,0), b, 2D, 0D));
	}
	
	@EventHandler
	public void onFurnitureBreak(FurnitureBreakEvent e){
		if(obj==null){return;}
		if(e.isCancelled()){return;}
		if(!e.getID().equals(obj)){return;}
		if(!e.canBuild()){return;}
		e.setCancelled(true);
		bed.setType(Material.AIR);
		e.remove();
		obj=null;
	}
	
	@EventHandler
	public void onFurnitureClick(FurnitureClickEvent e){
		if(obj==null){return;}
		if(e.isCancelled()){return;}
		if(!e.getID().equals(obj)){return;}
		e.setCancelled(true);
		if(!e.canBuild()){return;}
		Player p = e.getPlayer();
		if(p.getItemInHand().getType().equals(Material.INK_SACK)){
			lib.getColorManager().color(p, e.canBuild(), Material.BANNER, obj, ColorType.BANNER, 1);
		}else{
			for(ArmorStandPacket packet : manager.getArmorStandPacketByObjectID(obj)){
				if(packet.getName().equalsIgnoreCase("#SITZ#")){
					if(packet.getPessanger()==null){
						packet.setPessanger(p);
						packet.update();
					}
				}
			}
		}
	}
}
