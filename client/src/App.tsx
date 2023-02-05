import { alpha, Box, Container, Tab, Tabs, Typography } from '@mui/material';
import React from 'react';
import './App.css';
import EmployeeTable from './components/EmployeeTable';
import TopBar from './components/TopBar';

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          {children}
        </Box>
      )}
    </div>
  );
}


function App() {
  const [value, setValue] = React.useState(0);

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };

  return (
    <div className="App">
      <TopBar />
      <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
        <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
          <Tabs textColor='secondary' TabIndicatorProps={{ style: { background: "#f3f4fa" } }} value={value} onChange={handleChange} aria-label="basic tabs example">
            <Tab sx={{
              color: "#f3f4fa", "&:hover": {
                backgroundColor: alpha('#53e3c2', 0.45),
              }
            }} label="Software Engineers" />
            <Tab sx={{
              color: "#f3f4fa", "&:hover": {
                backgroundColor: alpha('#53e3c2', 0.45),
              }
            }} label="Client Experience" />
            <Tab sx={{
              color: "#f3f4fa", "&:hover": {
                backgroundColor: alpha('#53e3c2', 0.45),
              }
            }} label="Human Resources" />
          </Tabs>
          <TabPanel value={value} index={0}>
            <EmployeeTable />
          </TabPanel>
          <TabPanel value={value} index={1}></TabPanel>
          <TabPanel value={value} index={2}></TabPanel>
        </Box>
      </Container>
    </div>
  );
}

export default App;
