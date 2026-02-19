import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Module who's purpose is to return non-null or minimal valid outputs so the game can proceed. 
 * Does not concern itself with validity of moves
 */
public class AgentDriver {

    public void runInitialPhase(CatanAgent agent, int playerId, GameState state) {
        Objects.requireNonNull(agent, "agent");
        Objects.requireNonNull(state, "state");

        // assign agent player number 
        agent.init(playerId);

        // asks agent where to put first settlement and road, checks non-null
        Move settlement = agent.chooseInitialSettlement(state);
        requireNonNull(settlement, "chooseInitialSettlement returned null");

        Move road = agent.chooseInitialRoad(state);
        requireNonNull(road, "chooseInitialRoad returned null");
    }

    public void runNormalTurn(CatanAgent agent, GameState state) {
        Objects.requireNonNull(agent, "agent");
        Objects.requireNonNull(state, "state");

        Move move = agent.chooseMove(state);
        requireNonNull(move, "chooseMove returned null");
    }

    // run some special decisions 
    public void runEventHooks(CatanAgent agent, GameState state) {
        Objects.requireNonNull(agent, "agent");
        Objects.requireNonNull(state, "state");

        Map<ResourceType, Integer> discard = agent.chooseDiscard(state, 2);
        requireNonNull(discard, "chooseDiscard returned null");

        ResourceType chosen = agent.chooseResource(state);
        requireNonNull(chosen, "chooseResource returned null");

        List<Integer> targets = List.of(1, 2, 3);
        int target = agent.chooseRobberTarget(state, targets);
        if (!targets.contains(target)) {
            throw new IllegalStateException("chooseRobberTarget returned invalid target: " + target);
        }

        DevelopmentCard card = agent.chooseDevelopmentCard(state);
        requireNonNull(card, "chooseDevelopmentCard returned null");
    }

    private static <T> T requireNonNull(T value, String msg) {
        if (value == null) throw new IllegalStateException(msg);
        return value;
    }
}
