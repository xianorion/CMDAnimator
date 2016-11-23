package cmdAnimator.GameCanvasActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Aliases {

	private static HashMap<String, String> aliasList=  new HashMap<String, String>();
	private static ArrayList<String> restrictedAliases = new ArrayList<String>(Arrays.asList("help", "frame","add","image","text", "fps", "remove"
			,"goto", "run", "done", "clear", "alias"));

	public static boolean AddNewAlias(String alias, String replacedText) {
		if (!restrictedAliases.contains(alias.toLowerCase())) {
			aliasList.put(alias, replacedText);
			return true;
		} else
			return false;
	}

	public static String getReplacedTextFromAlias(String key) {
		return aliasList.get(key);
	}

	public static HashMap<String, String> getAliasList() {
		return aliasList;
	}

	public static String[] convertCommandWithAliasesIntoNormalCommand(String[] cmds) {
		ArrayList<String> command = new ArrayList<String>();
		String[] temp;
		for(int i = 0; i < cmds.length; i++){
			if(aliasList.containsKey(cmds[i])){
				temp = CommandParser.splitTextBasedOnDelimiters(aliasList.get(cmds[i]));
				for(int j = 0 ; j < temp.length; j++)
					command.add(temp[j]);
			}else
				command.add(cmds[i]);
			
		}
		return command.toArray(new String[0]);
	}
	
}
