import java.util.*;

class GameState {}
class Move {}
enum ResourceType { BRICK, LUMBER, WOOL, GRAIN, ORE } // example resources
enum DevelopmentCard { KNIGHT, MONOPOLY} // example cards

// provided interface
public interface CatanAgent {
    void init(int playerId);

    Move chooseInitialSettlement(GameState state);
    Move chooseInitialRoad(GameState state);
    Move chooseMove(GameState state);

    Map<ResourceType, Integer> chooseDiscard(GameState state, int discardCount);
    ResourceType chooseResource(GameState state);

    int chooseRobberTarget(GameState state, List<Integer> possibleTargets);
    
    DevelopmentCard chooseDevelopmentCard(GameState state);

}

