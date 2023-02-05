import { alpha, Button, Checkbox, Grid, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import axios from 'axios';
import React, { useState, useEffect } from 'react';

const EmployeeTable: React.FC = () => {
  const [employees, setEmployees] = useState<Array<any>>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [selected, setSelected] = React.useState<readonly string[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const result = await axios.get('api/v1/person');
        console.log(result);
        setEmployees(result.data);
        setLoading(false);
      } catch (err) {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  const deleteEmployee = async (names: readonly string[]) => {
    let newSelectedIds: number[] = [];

    for (const name of names) {
      for (const employee of employees) {
        if (employee.fullName === name) {
          newSelectedIds.push(employee.id);
          break;
        }
      }
    }

    console.log(newSelectedIds)
    newSelectedIds.forEach((id) => axios.delete(`api/v1/person/${id}`))
    window.location.reload()
  }

  if (loading) {
    return <div>Loading...</div>;
  }

  const handleClick = (event: React.MouseEvent<unknown>, name: string) => {
    const selectedIndex = selected.indexOf(name);
    let newSelected: readonly string[] = [];

    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, name);
      console.log(newSelected)
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(
        selected.slice(0, selectedIndex),
        selected.slice(selectedIndex + 1),
      );
    }
    setSelected(newSelected);
  };

  const isSelected = (name: string) => selected.indexOf(name) !== -1;


  return (
    <div>
      <Grid container spacing={2} direction="column">
        <Grid item>
          <TableContainer component={Paper} sx={{
            color: "#f3f4fa",
            backgroundColor: "#393945",
            borderRadius: 10,
            paddingTop: "10px",
            paddingRight: "5px",
            paddingLeft: "5px",
            paddingBottom: "3px"

          }}>
            <Table>
              <TableHead sx={{
                "& th": {
                  color: "#f3f4fa",
                  backgroundColor: "#393945"
                }
              }}>
                <TableRow>
                  <TableCell></TableCell>
                  <TableCell>ID</TableCell>
                  <TableCell>Full Name</TableCell>
                  <TableCell>Job Title</TableCell>
                  <TableCell>Team</TableCell>
                  <TableCell>Starting Date</TableCell>
                  <TableCell>Efficiency</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {employees.map((employee) => {
                  const isItemSelected = isSelected(employee.fullName);

                  return (
                    <TableRow
                      key={employee.fullName}
                      onClick={(event) => handleClick(event, employee.fullName)}
                      role="checkbox"
                      aria-checked={isItemSelected}
                      tabIndex={-1}
                      selected={isItemSelected}
                      sx={{
                        "&:hover": {
                          backgroundColor: alpha('#53e3c2', 0.45),
                        }
                      }}
                    >
                      <TableCell padding="checkbox">
                        <Checkbox
                          sx={{
                            color: 'white',
                            '&.Mui-checked': {
                              color: '#53e3c2',
                            }
                          }}
                          checked={isItemSelected}
                        />
                      </TableCell>
                      <TableCell component="th" scope="row"
                        sx={{ color: 'white' }}
                      >
                        {employee.id}
                      </TableCell>
                      <TableCell sx={{ color: 'white' }}>{employee.fullName}</TableCell>
                      <TableCell sx={{ color: 'white' }}>{employee.jobTitle}</TableCell>
                      <TableCell sx={{ color: 'white' }}>{employee.team}</TableCell>
                      <TableCell sx={{ color: 'white' }}>{employee.startingDate}</TableCell>
                      <TableCell sx={{ color: 'white' }}>{employee.efficiency}</TableCell>
                    </TableRow>
                  )
                })}
              </TableBody>
            </Table>
          </TableContainer>
        </Grid>
      </Grid>
      <Grid container
        justifyContent="flex-end"
        spacing={2}
        sx={{ mt: 1 }}
      >
        <Grid item>
          <Button disabled={selected.length <= 0} variant='outlined' sx={{ backgroundColor: '#55d8bb', color: "#f3f4fa" }}>Compare</Button>
        </Grid>
        <Grid item>
          <Button onClick={() =>{
            deleteEmployee(selected)
          }} disabled={selected.length <= 0} variant='outlined' sx={{ backgroundColor: '#55d8bb', color: "#f3f4fa" }}>Fire</Button>
        </Grid>
      </Grid>
    </div>
  );
};

export default EmployeeTable;
