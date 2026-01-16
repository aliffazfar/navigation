package com.navigation.reactnative;

import androidx.transition.Transition;

import java.util.HashSet;

public class SharedElementMotion {
    private final SceneFragment enterScene;
    private final SceneFragment scene;
    private final HashSet<String> sharedElements;
    private final HashSet<String> loadedSharedElements = new HashSet<>();
    private final boolean containerTransform;

    SharedElementMotion(SceneFragment enterScene, SceneFragment scene, HashSet<String> sharedElements, boolean containerTransform) {
        this.sharedElements = sharedElements;
        this.enterScene = enterScene;
        this.scene = scene;
        this.containerTransform = containerTransform;
    }

    void load(SharedElementView sharedElementView) {
        if (sharedElements.contains(sharedElementView.getTransitionName()) && !loadedSharedElements.contains(sharedElementView.getTransitionName())) {
            loadedSharedElements.add(sharedElementView.getTransitionName());
            if(sharedElements.size() == loadedSharedElements.size()) {
                Transition transition = sharedElementView.getTransition(containerTransform, enterScene == scene);
                enterScene.setSharedElementEnterTransition(transition);
                enterScene.setSharedElementReturnTransition(transition);
                scene.startPostponedEnterTransition();
                scene.getScene().sharedElementMotion = null;
            }
        }
    }
}
