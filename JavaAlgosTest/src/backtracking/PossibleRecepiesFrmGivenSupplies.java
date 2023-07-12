// 2115. Find All Possible Recipes from Given Supplies
/*
You have information about n different recipes. You are given a string array recipes and a
2D string array ingredients.

The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients
from ingredients[i].

Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may
contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have, and
you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.

Example 1:

Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
Output: ["bread"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".

Example 2:

Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]],
supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".

Example 3:

Input: recipes = ["bread","sandwich","burger"],
ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]],
supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich","burger"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".

Constraints:

n == recipes.length == ingredients.length
1 <= n <= 100
1 <= ingredients[i].length, supplies.length <= 100
1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
All the values of recipes and supplies combined are unique.
Each ingredients[i] does not contain any duplicate values.
*/

package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class PossibleRecepiesFrmGivenSuppliesUtil_TLE{

    int linearSearch(ArrayList<String>supplies, String vals){
        int sz=supplies.size();
        for(int i=0; i<sz; ++i){
            if(supplies.get(i).equals(vals)) return i;
        }
        return -1;
    }

    void dfs(ArrayList<String>recipes, ArrayList<ArrayList<String>>ingredients, ArrayList<String>supplies,
                          int sz, int idx,
                          ArrayList<String>curans, HashSet<Integer>seen, HashMap<Integer, ArrayList<String>>memo){
        if(idx>=sz) return;
        if(memo.containsKey(idx)){
            return;
        }
        String currecipe=recipes.get(idx);
        ArrayList<String>ingreneed=ingredients.get(idx);
        boolean flag=false;
        for(String vals: ingreneed){
            int itr=linearSearch(supplies, vals);
            if(itr==-1){
                flag=true;
                break;
            }
        }
        if(!flag){
            curans.add(currecipe);
            supplies.add(currecipe);
        }
        seen.add(idx);
        for(int i=0; i<sz; ++i){
            if(!seen.contains(i)){
                dfs(recipes, ingredients, supplies, sz, i, curans, seen, memo);
            }
        }
        memo.put(idx, curans);
        return;
    }

    public ArrayList<String> findAllRecepies(ArrayList<String>recipes, ArrayList<ArrayList<String>>ingredients,
                                             ArrayList<String>supplies){
        int sz=recipes.size();
        ArrayList<String>fnans=new ArrayList<>();
        for(int i=0; i<sz; ++i){
            ArrayList<String>curans=new ArrayList<>();
            HashSet<Integer>seen=new HashSet<>();
            HashMap<Integer, ArrayList<String>>memo=new HashMap<>();
            dfs(recipes, ingredients, supplies, sz, i, curans, seen, memo);
            if(curans.size()>fnans.size()){
                fnans= new ArrayList<>(curans);
            }
        }
        return fnans;
    }
}

public class PossibleRecepiesFrmGivenSupplies {

    public static void main(String[] args){
        PossibleRecepiesFrmGivenSuppliesUtil_TLE obj=new PossibleRecepiesFrmGivenSuppliesUtil_TLE();
        String[] recipesarr=new String[]{"bread","sandwich"};
        String[][] ingredientsarr=new String[][]{{"yeast","flour"},{"bread","meat"}};
        String[] suppliesarr=new String[]{"yeast","flour","meat"};
        ArrayList<String>recipes=new ArrayList<>(Arrays.asList(recipesarr));
        ArrayList<ArrayList<String>>ingredients=new ArrayList<>();
        for(String[] ingre: ingredientsarr){
            ArrayList<String>temp=new ArrayList<>(Arrays.asList(ingre));
            ingredients.add(temp);
        }
        ArrayList<String>supplies=new ArrayList<>(Arrays.asList(suppliesarr));
        ArrayList<String>ans=obj.findAllRecepies(recipes, ingredients, supplies);
        System.out.println(ans.toString());
    }
}
