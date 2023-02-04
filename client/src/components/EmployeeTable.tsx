import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import axios from 'axios';
import React, { useState, useEffect } from 'react';

const EmployeeTable: React.FC = () => {
    const [employees, setEmployees] = useState<Array<any>>([]);
    const [loading, setLoading] = useState<boolean>(false);

    useEffect(() => {
        console.log("what")
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
        console.log("inside useeffect")
        fetchData();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Full Name</TableCell>
                        <TableCell>Position</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {employees.map((employee) => (
                        <TableRow key={employee.id}>
                            <TableCell component="th" scope="row">
                                {employee.id}
                            </TableCell>
                            <TableCell>{employee.fullName}</TableCell>
                            <TableCell>{employee.jobTitle}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default EmployeeTable;
