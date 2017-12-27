package sgw.projectusers.dagger.components;


import dagger.Subcomponent;
import sgw.projectusers.view.ui.activities.MainActivity;

@Subcomponent
/** Activity component. Used to handle activity related DI into activities. */

public interface ActivityComponent {

    void inject(MainActivity activity);
}
