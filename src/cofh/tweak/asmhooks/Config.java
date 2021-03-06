package cofh.tweak.asmhooks;

import cofh.repack.tweak.codechicken.lib.config.ConfigFile;
import cofh.repack.tweak.codechicken.lib.config.ConfigTag;
import cofh.tweak.asm.LoadingPlugin;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Config {

	static ConfigFile config;

	public static void loadConfig(File file) {

		{
			File folder = new File(file, "config/cofh/tweak");
			if (folder.exists() || folder.mkdirs()) {
				folder = new File(folder, "common.prop");
				if (folder.exists()) {

					Properties config = new Properties();
					try {
						config.load(new FileReader(folder));
					} catch (Throwable e) {
					}

					stackItems = Boolean.parseBoolean(config.getProperty("StackItems", String.valueOf(stackItems)));
					collideEntities = Boolean.parseBoolean(config.getProperty("EntityCollisions", String.valueOf(collideEntities)));
					animateTextures = Boolean.parseBoolean(config.getProperty("AnimatedTextures", String.valueOf(animateTextures)));
					lightChunks = Boolean.parseBoolean(config.getProperty("ChunkLighting", String.valueOf(lightChunks)));
					agressiveCulling = Boolean.parseBoolean(config.getProperty("AgressiveCulling", String.valueOf(agressiveCulling)));
					distantCulling = Boolean.parseBoolean(config.getProperty("DistantCulling", String.valueOf(distantCulling)));
					fastBlocks = Boolean.parseBoolean(config.getProperty("FastClientSetBlock", String.valueOf(fastBlocks)));
					agressiveAICulling = Boolean.parseBoolean(config.getProperty("AgressiveAIReduction", String.valueOf(agressiveAICulling)));

					folder.deleteOnExit();
				}
			}
		}
		file = new File(file, "config/cofh/tweak/common.cfg");

		config = new ConfigFile(file);

		{
			config.getTag("Client").useBraces().setNewLineMode(2);
			config.getTag("Server").useBraces().setNewLineMode(2);
			config.getTag("Profiling").useBraces().setNewLineMode(2);
		}

		ConfigTag tag;
		String comment;

		comment = "If true, EntityItems will attempt to stack in-world. This overrides the setting in CoFHCore.";
		(tag = config.getTag("StackItems")).setComment(comment);
		stackItems = tag.getBooleanValue(stackItems);
		comment = "If true, Entities will attempt to collide/push with each other while moving.";
		(tag = config.getTag("EntityCollisions")).setComment(comment);
		collideEntities = tag.getBooleanValue(collideEntities);
		comment = "If true, the client will perform in-depth lighting on chunks and the server will redundantly update lighting near players.";
		(tag = config.getTag("ChunkLighting")).setComment(comment);
		lightChunks = tag.getBooleanValue(lightChunks);

		comment = "If true, in-depth AI profiling information will be available via mojang's profiler.";
		(tag = config.getTag("Profiling.AllowAIProfiling")).setComment(comment);
		allowProfilingAI = tag.getBooleanValue(allowProfilingAI);
		comment = "If true, in-depth Lighting profiling information will be available via mojang's profiler.";
		(tag = config.getTag("Profiling.AllowLightingProfiling")).setComment(comment);
		allowProfilingLighting = tag.getBooleanValue(allowProfilingLighting);

		comment = "If true, the server-side AI will not process as frequently. On heavily burdened servers this will manifest as mobs not doing anything every few ticks";
		(tag = config.getTag("Server.AggressiveAIReduction")).setComment(comment);
		agressiveAICulling = tag.getBooleanValue(agressiveAICulling);
		comment = "If true, the server will perform in-depth lighting on chunks.";
		(tag = config.getTag("Server.ChunkLighting")).setComment(comment);
		fullLightChunks = tag.getBooleanValue(fullLightChunks);

		comment = "If true, textures will animate. This overrides the setting in CoFHCore.";
		(tag = config.getTag("Client.AnimatedTextures")).setComment(comment);
		animateTextures = tag.getBooleanValue(animateTextures);
		comment = "If true, entities will be aggressively culled from rendering when tightly packed. VSync should be turned on if enabled";
		(tag = config.getTag("Client.AggressiveCulling")).setComment(comment);
		agressiveCulling = tag.getBooleanValue(agressiveCulling);
		comment = "If true, entities will be aggressively culled from rendering when far away";
		(tag = config.getTag("Client.DistantCulling")).setComment(comment);
		distantCulling = tag.getBooleanValue(distantCulling);
		comment = "If true, the client will not process lighting updates when blocks are changed via packets";
		(tag = config.getTag("Client.FastSetBlock")).setComment(comment);
		fastBlocks = tag.getBooleanValue(fastBlocks);
		comment = "If true, the current FPS will be displayed in the upper left without F3";
		(tag = config.getTag("Client.ShowFPS")).setComment(comment);
		showFPS = tag.getBooleanValue(showFPS);
		comment = "If -1, the default will happen, if 0 leaves will always be solid, if 1 leaves will always be transparent. May not work on all mod leaves";
		(tag = config.getTag("Client.OverrideFancyLeaves")).setComment(comment);
		overrideFancyLeaves = tag.getIntValue(overrideFancyLeaves);
	}

	public static boolean allowProfilingAI = true;
	public static boolean allowProfilingLighting = true;
	public static boolean stackItems = true;
	public static boolean collideEntities = true;
	public static boolean animateTextures = true;
	public static boolean fastBlocks = false;
	public static boolean lightChunks = true;
	public static boolean fullLightChunks = true;
	public static boolean agressiveCulling = false;
	public static boolean distantCulling = false;
	public static boolean agressiveAICulling = false;
	public static boolean showFPS = !LoadingPlugin.obfuscated;
	public static int overrideFancyLeaves = -1;

}
