package com.ericsson.graduate.group2.CIstabilityproject.controllerTests;


import com.ericsson.graduate.group2.CIstabilityproject.Controllers.UiController;
import com.ericsson.graduate.group2.CIstabilityproject.entities.BuildEntity;
import com.ericsson.graduate.group2.CIstabilityproject.entities.T1Entity;
import com.ericsson.graduate.group2.CIstabilityproject.object.Build;
import com.ericsson.graduate.group2.CIstabilityproject.object.CustomYear;
import com.ericsson.graduate.group2.CIstabilityproject.repositories.BuildRepository;
import com.ericsson.graduate.group2.CIstabilityproject.repositories.JobNameRepository;

import com.ericsson.graduate.group2.CIstabilityproject.object.FrontEndBean;
import com.ericsson.graduate.group2.CIstabilityproject.services.BuildService;
import com.ericsson.graduate.group2.CIstabilityproject.services.JobNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UIControllerTests {


    @MockBean
    private BuildService mockBuildRepository = mock(BuildService.class);

    @MockBean
    private JobNameService mockJobNameRepository = mock(JobNameService.class);

    @Autowired
    private UiController controller;

    private List<BuildEntity> testBEs = new ArrayList<BuildEntity>();
    private List<T1Entity> testT1s = new ArrayList<T1Entity>();

    @BeforeEach
    public void setup() {
        /*
        private int jobId;
        private int buildId;
        private long start_time;
        private String status;
         */
        BuildEntity testEntity1 = new BuildEntity();
        BuildEntity testEntity2 = new BuildEntity();

        testEntity1.setJob_id(1);
        testEntity2.setJob_id(2);

        testEntity1.setBuild_id(1);
        testEntity2.setBuild_id(2);

        testEntity1.setTimestamp("1649412559009");
        testEntity2.setTimestamp("1649412559009");

        testEntity1.setStatus("SUCCESS");
        testEntity2.setStatus("FAILURE");

        T1Entity testJob1 = new T1Entity();
        T1Entity testJob2 = new T1Entity();

        testJob1.setJob_name("job one");
        testJob2.setJob_name("job two");

        testJob1.setId(1);
        testJob2.setId(2);

        testBEs.add(testEntity1);
        testBEs.add(testEntity2);
        testT1s.add(testJob1);
        testT1s.add(testJob2);
        controller = new UiController();
        controller.setJobNameService(mockJobNameRepository);
        controller.setBuildService(mockBuildRepository);
    }

    @Test
    public void testDataRequestFromUi() {
        when(mockBuildRepository.findAll()).thenReturn(testBEs);
        when(mockJobNameRepository.findAll()).thenReturn(testT1s);
        List<String> names = new ArrayList<>();
        names.add("job one");
        FrontEndBean AAbean = new FrontEndBean(names, "1-1-1-0-0", "3000-12-12-0-0");
        ResponseEntity entity = controller.dataRequestFromUI(AAbean);
        assert(200 == entity.getStatusCodeValue());
    }

   /* @Test
    public void testDidXComeAfterY() {
        assertEquals(controller.didXComeAfterY(new CustomYear(2020, 3, 6, 0, 0), new CustomYear(2020, 5, 6, 0, 0)), false);
        assertEquals(controller.didXComeAfterY(new CustomYear(2020, 3, 6, 0, 0), new CustomYear(2020, 1, 6, 0, 0)), true);

    }

    */

    @Test
    public void testGetCustomYear() {
        CustomYear testYear = controller.getCustomYear("2020-3-6-0-0");
        assertEquals(testYear.getYear(), 2020);
        assertEquals(testYear.getMonth(), 3);
        assertEquals(testYear.getDay(), 6);
    }

    /*@Test
    public void testGetJobId() {
        when(mockJobNameRepository.findAll()).thenReturn(testT1s);
        int res = controller.getJobId("job one");
        assertEquals(res, 1);
    }

     */

    @Test
    public void testGetAllValidBuilds() {
        CustomYear testStart = new CustomYear(1, 1, 1, 0, 0);
        CustomYear testEnd = new CustomYear(3000, 12, 30, 0, 0);
        List<Integer> testNums = new ArrayList<>();
        testNums.add(1);
        List<Build> resBuilds = controller.getAllValidBuilds(testStart, testEnd, testNums, testBEs);
        assertEquals(resBuilds.size(), 1);
        assertEquals(resBuilds.get(0).getBuildSuccess(), true);
    }
    /*
    MUSTS
        - Pull data from database
        - Send data to frontend
        - Do operations on pulled data
        - Sent data must be in JSON format

    SHOULD
        - Be able to calculate time difference in builds (req 3)
        - Operate independently from frontend & database
        - Have unit tests on all object & entitiy classes
        -

    COULD
        - Expand based on time instead of date
        -
     */
}
