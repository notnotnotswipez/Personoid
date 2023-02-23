package com.personoid.api.pathfinding.node.evaluator;

import com.personoid.api.pathfinding.NodeContext;
import com.personoid.api.pathfinding.node.Node;
import com.personoid.api.pathfinding.utils.BlockPos;
import com.personoid.api.utils.types.BlockTags;

public class ClimbNodeEvaluator extends NodeEvaluator {
    @Override
    public Node apply(Node from, BlockPos to, NodeContext context) {
        BlockPos climb = to;
        int climbDistance = 0;
        while (isClimbable(climb)) {
            climb = climb.above();
            climbDistance++;
        }
        if (climbDistance > 0) {
            return createNode(climb, climbDistance);
        }
        return null;
    }

    private boolean isClimbable(BlockPos pos) {
        return BlockTags.CLIMBABLE.is(pos.toBlock(context.getWorld()));
    }
}