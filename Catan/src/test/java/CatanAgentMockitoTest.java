import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.Test;
import org.mockito.InOrder;

public class CatanAgentMockitoTest {

    // test verifying the order of initial operations are correct
    @Test
    public void testInitialPhase_callsInitSettlementThenRoad() {
        // create mocks of involved objects
        CatanAgent agent = mock(CatanAgent.class);
        GameState state = mock(GameState.class);

        Move settlement = mock(Move.class);
        Move road = mock(Move.class);

        // stub predefined behaviour 
        when(agent.chooseInitialSettlement(state)).thenReturn(settlement);
        when(agent.chooseInitialRoad(state)).thenReturn(road);

        AgentDriver driver = new AgentDriver();

        driver.runInitialPhase(agent, 0, state);

        // verify first 3 calls happened in order
        InOrder inOrder = inOrder(agent);
        inOrder.verify(agent).init(0);
        inOrder.verify(agent).chooseInitialSettlement(state);
        inOrder.verify(agent).chooseInitialRoad(state);

        verifyNoMoreInteractions(agent);
    }

    // test verifying that the game asks the agent for exactly one move without any other interaction
    @Test
    public void testNormalTurn_callsChooseMove() {
        CatanAgent agent = mock(CatanAgent.class);
        GameState state = mock(GameState.class);

        // create mock move and stube the agents behaviour
        Move move = mock(Move.class);
        when(agent.chooseMove(state)).thenReturn(move);

        // run actual code 
        new AgentDriver().runNormalTurn(agent, state);

        // ensure chooseMove(state) called exactly once
        verify(agent, times(1)).chooseMove(state);
        verifyNoMoreInteractions(agent);
    }

    // test verifying robber interaction calls each appropriate method exactly once
    @Test
    public void testEventHooks_callsDiscardResourceRobberDevCard() {
        // create fake agent and game state
        CatanAgent agent = mock(CatanAgent.class);
        GameState state = mock(GameState.class);

        // stub return values 
        when(agent.chooseDiscard(state, 2)).thenReturn(Map.of(ResourceType.LUMBER, 1));
        when(agent.chooseResource(state)).thenReturn(ResourceType.BRICK);
        when(agent.chooseRobberTarget(eq(state), anyList())).thenReturn(2);
        when(agent.chooseDevelopmentCard(state)).thenReturn(DevelopmentCard.KNIGHT);

        // run code being tested
        new AgentDriver().runEventHooks(agent, state);

        // verify all method calls were made
        verify(agent).chooseDiscard(state, 2);
        verify(agent).chooseResource(state);
        verify(agent).chooseRobberTarget(eq(state), anyList());
        verify(agent).chooseDevelopmentCard(state);

        verifyNoMoreInteractions(agent);
    }

    @Test(expected = IllegalStateException.class)
    public void testNormalTurn_failsIfAgentReturnsNullMove() {
        CatanAgent agent = mock(CatanAgent.class);
        GameState state = mock(GameState.class);

        // default for unstubbed is null anyway, but we make it explicit
        when(agent.chooseMove(state)).thenReturn(null);

        new AgentDriver().runNormalTurn(agent, state);
    }
}
