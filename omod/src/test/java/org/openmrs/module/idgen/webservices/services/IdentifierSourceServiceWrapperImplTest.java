package org.openmrs.module.idgen.webservices.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.openmrs.api.context.Context;
import org.openmrs.module.idgen.IdentifierSource;
import org.openmrs.module.idgen.SequentialIdentifierGenerator;
import org.openmrs.module.idgen.service.IdentifierSourceService;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Context.class)
public class IdentifierSourceServiceWrapperImplTest {
    @Mock
    private SequentialIdentifierGenerator identifierSource;
    @Mock
    private IdentifierSourceService identifierSourceService;

    private IdentifierSourceServiceWrapperImpl identifierSourceServiceWrapperImpl;

    @Before
    public void before() throws Exception {
        initMocks(this);
        mockStatic(Context.class);
        identifierSourceServiceWrapperImpl = new IdentifierSourceServiceWrapperImpl();
    }

    @Test
    public void shouldGenerateIdentifier() throws Exception {
        when(Context.getService(IdentifierSourceService.class)).thenReturn(identifierSourceService);
        when(identifierSource.getName()).thenReturn("OPD");
        when(identifierSourceService.getAllIdentifierSources(false)).thenReturn(new ArrayList<IdentifierSource>() {{
            this.add(identifierSource);
        }});

        identifierSourceServiceWrapperImpl.generateIdentifier("OPD", "New HIV Patient");

        verify(identifierSourceService).generateIdentifier(identifierSource, "New HIV Patient");
    }

    @Test
    public void shouldGetSequenceValue() throws Exception {
        when(Context.getService(IdentifierSourceService.class)).thenReturn(identifierSourceService);

        ArrayList<IdentifierSource> identifierSources = new ArrayList<IdentifierSource>();
        SequentialIdentifierGenerator sequentialIdentifierGenerator = new SequentialIdentifierGenerator();
        sequentialIdentifierGenerator.setName("GAN");
        identifierSources.add(sequentialIdentifierGenerator);

        when(identifierSourceService.getAllIdentifierSources(false)).thenReturn(identifierSources);
        when(identifierSourceService.getSequenceValue(sequentialIdentifierGenerator)).thenReturn((long)123456);

        String identifier = identifierSourceServiceWrapperImpl.getSequenceValue("GAN");
        verify(identifierSourceService).getSequenceValue(sequentialIdentifierGenerator);
        assertEquals("123456", identifier);
    }

    @Test
    public void shouldSaveSequenceValue() throws Exception {
        when(Context.getService(IdentifierSourceService.class)).thenReturn(identifierSourceService);

        ArrayList<IdentifierSource> identifierSources = new ArrayList<IdentifierSource>();
        SequentialIdentifierGenerator sequentialIdentifierGenerator = new SequentialIdentifierGenerator();
        sequentialIdentifierGenerator.setName("GAN");
        identifierSources.add(sequentialIdentifierGenerator);

        when(identifierSourceService.getAllIdentifierSources(false)).thenReturn(identifierSources);

        Long identifier = identifierSourceServiceWrapperImpl.saveSequenceValue((long) 1234567, "GAN");
        verify(identifierSourceService).saveSequenceValue(sequentialIdentifierGenerator, (long) 1234567);
        assertEquals("1234567", identifier.toString());
    }

    @Test
    public void shouldGetAllIdentifierSources() {
        when(Context.getService(IdentifierSourceService.class)).thenReturn(identifierSourceService);

        ArrayList<IdentifierSource> identifierSources = new ArrayList<IdentifierSource>();
        SequentialIdentifierGenerator sequentialIdentifierGenerator = new SequentialIdentifierGenerator();
        sequentialIdentifierGenerator.setName("name");
        sequentialIdentifierGenerator.setUuid("uuid");
        sequentialIdentifierGenerator.setPrefix("GAN");
        identifierSources.add(sequentialIdentifierGenerator);

        when(identifierSourceService.getAllIdentifierSources(false)).thenReturn(identifierSources);

        List<org.openmrs.module.idgen.contract.IdentifierSource> allIdentifierSources = identifierSourceServiceWrapperImpl.getAllIdentifierSources();

        assertEquals(allIdentifierSources.size(), 1);
        assertEquals("name",(allIdentifierSources.get(0).getName()));
        assertEquals("uuid",(allIdentifierSources.get(0).getUuid()));
        assertEquals("GAN",(allIdentifierSources.get(0).getPrefix()));
    }

}