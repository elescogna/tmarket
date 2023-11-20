package use_case.post;

import use_case.profile.ProfileInputData;

public interface PostInputBoundary {
    void execute (PostInputData postInputData);
}
