import { IExtUser } from 'app/shared/model/ext-user.model';
import React from 'react';
import { Link } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { getEntities } from './ext-user.reducer';

interface IExtUserProps {}

interface IExtUserState {
  loading: boolean;
  extUserList: IExtUser[];
  totalItems: number;
}

export default class ExtUser extends React.Component<IExtUserProps, IExtUserState> {
  constructor(props: IExtUserProps) {
    super(props);
    this.state = {
      loading: false,
      extUserList: [],
      totalItems: 0
    };
  }

  componentDidMount() {
    this.loadList();
  }

  loadList() {
    this.setState({loading: true});
    getEntities()
      .then(res => {
        this.setState({ extUserList: res.data });
      })
      .catch(err => console.error('Failed to get the list of transaction accounts', err))
      .finally(() => this.setState({loading: false}));
  }

  render() {
    return (
      <div>
        <h2 id="ext-user-heading" data-cy="ExtUserHeading">
          Members
          <div className="d-flex justify-content-end">
            <Button className="me-2" color="primary" onClick={this.loadList.bind(this)} disabled={this.state.loading}>
              Refresh List
            </Button>
          </div>
        </h2>
        <div className="table-responsive">
          {this.state.extUserList && this.state.extUserList.length > 0 ? (
            <Table responsive size="sm">
              <thead>
                <tr>
                  <th>
                    Name
                  </th>
                  <th>
                    First Name
                  </th>
                  <th>
                    Last Name
                  </th>
                  <th>
                    Birth Date
                  </th>
                  <th>
                    Address
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {this.state.extUserList.map(extUser => (
                  <tr key={`entity-${extUser.id}`} data-cy="entityTable">
                    <td>{extUser.name}</td>
                    <td>{extUser.firstName}</td>
                    <td>{extUser.lastName}</td>
                    <td>{extUser.birthDate}</td>
                    <td>{extUser.address}</td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`./${extUser.id}`} color="primary" size="sm" data-cy="entityDetailsButton">
                          <span className="d-none d-md-inline">View</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !this.state.loading && <div className="alert alert-warning">No Users found</div>
          )}
        </div>
      </div>
    );
  }
}
